package com.synchz.rick_morty.data.repository

import com.synchz.rick_morty.data.model.CharacterEntity
import com.synchz.rick_morty.data.model.EpisodeEntity
import com.synchz.rick_morty.data.model.LocationEntity

interface LocalDataSource {
    //    Characters
    suspend fun getCharacters(): List<CharacterEntity>
    suspend fun getCharacterById(characterId: Long): CharacterEntity
    suspend fun saveCharacters(listCharacters: List<CharacterEntity>)
    suspend fun getCharactersCount(): Int
    suspend fun clearCharacters()
    //    Locations
    suspend fun getLocations(): List<LocationEntity>
    suspend fun getLocationById(locationId: Long): LocationEntity
    suspend fun saveLocations(listLocations: List<LocationEntity>)
    suspend fun getLocationsCount(): Int
    suspend fun clearLocations()
    //    Episodes
    suspend fun getEpisodes(): List<EpisodeEntity>
    suspend fun getEpisodeById(episodeId: Long): EpisodeEntity
    suspend fun saveEpisodes(listEpisodes: List<EpisodeEntity>)
    suspend fun getEpisodesCount(): Int
    suspend fun clearEpisodes()

}