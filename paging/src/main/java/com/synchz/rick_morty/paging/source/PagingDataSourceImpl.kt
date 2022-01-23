package com.synchz.rick_morty.paging.source

import androidx.paging.*
import com.synchz.rick_morty.data.dataSource.PagingDataSource
import com.synchz.rick_morty.data.model.CharacterEntity
import com.synchz.rick_morty.data.model.EpisodeEntity
import com.synchz.rick_morty.data.model.LocationEntity
import com.synchz.rick_morty.local.database.dao.CharacterDao
import com.synchz.rick_morty.local.mapper.CharacterLocalMapper
import com.synchz.rick_morty.paging.mediators.CharacterListRemoteMediator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalPagingApi
class PagingDataSourceImpl @Inject constructor(
    private val characterDao: CharacterDao,
    private val characterLocalMapper: CharacterLocalMapper,
    private val characterListRemoteMediator: CharacterListRemoteMediator
    ) : PagingDataSource {

    override suspend fun getCharacters(): Flow<PagingData<CharacterEntity>> {
        return Pager(
            config = PagingConfig(20, enablePlaceholders = true),
            remoteMediator = characterListRemoteMediator
        ) {
            characterDao.getCharacters()
        }.flow.map { pagingData ->
            pagingData.map {
                characterLocalMapper.to(it)
            }
        }
    }

    override suspend fun getLocations(): Flow<PagingData<LocationEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getEpisodes(): Flow<PagingData<EpisodeEntity>> {
        TODO("Not yet implemented")
    }
}