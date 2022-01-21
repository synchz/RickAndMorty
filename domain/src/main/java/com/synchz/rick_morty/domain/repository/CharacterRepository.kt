package com.synchz.rick_morty.domain.repository

import kotlinx.coroutines.flow.Flow


interface CharacterRepository {
    suspend fun getCharacters(pageNo: Int): Flow<List<com.synchz.rick_morty.domain.entities.Character>>
    suspend fun getCharacterById(characterId: Long): Flow<com.synchz.rick_morty.domain.entities.Character>
//    suspend fun saveCharacters(listCharacters: List<com.synchz.rick_morty.domain.entities.Character>)
//    suspend fun getCharactersCount(): Flow<Int>
//    suspend fun clearCharacters()
}
