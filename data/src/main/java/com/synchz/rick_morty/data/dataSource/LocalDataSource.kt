package com.synchz.rick_morty.data.dataSource

import com.synchz.rick_morty.data.model.CharacterEntity
import com.synchz.rick_morty.data.model.CharacterRemoteKeysEntity
import com.synchz.rick_morty.data.model.EpisodeEntity
import com.synchz.rick_morty.data.model.LocationEntity

interface LocalDataSource {
    //    Characters
    suspend fun saveCharacters(listCharacters: List<CharacterEntity>)
    suspend fun saveCharactersCount(size: Int)
    suspend fun getCharactersCount(): Int
    suspend fun clearCharacters()
    suspend fun getCharacterRemoteKeyById(id: Long): CharacterRemoteKeysEntity?
    suspend fun saveCharacterRemoteKey(key: CharacterRemoteKeysEntity)
    suspend fun clearCharacterRemoteKey()
    //    Locations
    suspend fun saveLocations(listLocations: List<LocationEntity>)
    suspend fun saveLocationsCount(size: Int)
    suspend fun getLocationsCount(): Int
    suspend fun clearLocations()
    //    Episodes
    suspend fun saveEpisodes(listEpisodes: List<EpisodeEntity>)
    suspend fun saveEpisodeCount(size: Int)
    suspend fun getEpisodeCount(): Int
    suspend fun clearEpisodes()

}