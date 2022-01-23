package com.synchz.rick_morty.data.dataSource

import com.synchz.rick_morty.data.model.*


interface RemoteDatasource {
    suspend fun getCharacters(pageNo: Int): CharacterListEntity
    suspend fun getCharacterById(characterId: Long): CharacterEntity
    suspend fun getAllLocations(pageNo: Int): LocationListEntity
    suspend fun getLocationById(locationId: Long): LocationEntity
    suspend fun getEpisodes(pageNo: Int): EpisodeListEntity
    suspend fun getEpisodeById(episodeId: Long): EpisodeEntity
}