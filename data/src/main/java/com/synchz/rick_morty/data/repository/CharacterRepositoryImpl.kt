package com.synchz.rick_morty.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.synchz.rick_morty.data.dataSource.LocalDataSource
import com.synchz.rick_morty.data.dataSource.PagingDataSource
import com.synchz.rick_morty.data.dataSource.RemoteDatasource
import com.synchz.rick_morty.data.mapper.CharacterMapper
import com.synchz.rick_morty.domain.entities.Character
import com.synchz.rick_morty.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val pagingDataSource: PagingDataSource,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDatasource,
    private val characterMapper: CharacterMapper
) : CharacterRepository {
    
    override suspend fun fetchCharactersFromServer(pageNo: Int): Flow<List<Character>> = flow {
        val characterList = remoteDataSource.getCharacters(pageNo).let {
            if (pageNo <= 1) localDataSource.clearCharacters()
            localDataSource.saveCharacters(it.results)
            it.results.map { character -> characterMapper.to(character) }
        }
        emit(characterList)
    }

    override suspend fun getCharactersDataSource(): Flow<PagingData<Character>> {
        return pagingDataSource.getCharacters().map { pagingData->
            pagingData.map{
                characterMapper.to(it)
            }
        }
    }

    override suspend fun getCharacterById(characterId: Long): Flow<Character> {
        return flow {
            val character = remoteDataSource.getCharacterById(characterId).let {
                characterMapper.to(it)
            }
            emit(character)
        }
    }
}