package com.synchz.rick_morty.data.repository

import com.synchz.rick_morty.data.model.CharacterEntity
import com.synchz.rick_morty.data.model.EpisodeEntity
import com.synchz.rick_morty.data.model.LocationEntity


interface RemoteDatasource {
    suspend fun getCharacters(pageNo: Int): List<CharacterEntity>
    suspend fun getCharacterById(characterId: Long): CharacterEntity
    suspend fun getAllLocations(pageNo: Int): List<LocationEntity>
    suspend fun getLocationById(locationId: Long): LocationEntity
    suspend fun getEpisodes(pageNo: Int): List<EpisodeEntity>
    suspend fun getEpisodeById(episodeId: Long): EpisodeEntity
}