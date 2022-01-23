package com.synchz.rick_morty.paging.mediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.synchz.rick_morty.data.dataSource.LocalDataSource
import com.synchz.rick_morty.data.dataSource.RemoteDatasource
import com.synchz.rick_morty.data.model.CharacterRemoteKeysEntity
import com.synchz.rick_morty.local.model.CharacterLocal
import java.io.IOException
import java.io.InvalidObjectException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CharacterListRemoteMediator @Inject constructor(
    private val initialPage: Int = 1,
    private val localDataSource: LocalDataSource,
    private val remoteDatasource: RemoteDatasource
) : RemoteMediator<Int, CharacterLocal>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterLocal>
    ): MediatorResult {
        return try{
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: initialPage
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(true)
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                        ?: throw InvalidObjectException("Result is empty")
                    remoteKeys.nextKey ?: return MediatorResult.Success(true)
                }
            }
            remoteDatasource.getCharacters(page).let {
                val endOfPaginationReached = it.info.next == null
                if (loadType == LoadType.REFRESH) {
                    localDataSource.clearCharacterRemoteKey()
                    localDataSource.clearCharacters()
                }
                val prevKey = if (page == initialPage) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                it.results.forEach { characterEntity ->
                    localDataSource.saveCharacterRemoteKey(
                        CharacterRemoteKeysEntity(
                            characterId = characterEntity.id,
                            prevKey = prevKey,
                            nextKey = nextKey
                        )
                    )
                }
                localDataSource.saveCharacters(it.results)
                MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            }
        }catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, CharacterLocal>): CharacterRemoteKeysEntity? {
        return state.lastItemOrNull()?.let { character ->
            localDataSource.getCharacterRemoteKeyById(character.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, CharacterLocal>): CharacterRemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                localDataSource.getCharacterRemoteKeyById(id)
            }
        }
    }
}