package com.synchz.rick_morty.domain.repository

import androidx.paging.DataSource
import com.synchz.rick_morty.domain.entities.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun fetchCharactersFromServer(pageNo: Int): Flow<List<Character>>
    suspend fun getCharactersDataSource(): DataSource.Factory<Int, Character>
    suspend fun getCharacterById(characterId: Long): Flow<Character>
//    suspend fun saveCharacters(listCharacters: List<Character>)
//    suspend fun getCharactersCount(): Flow<Int>
//    suspend fun clearCharacters()
}
