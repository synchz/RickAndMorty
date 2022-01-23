package com.synchz.rick_morty.data.dataSource

import androidx.paging.DataSource
import com.synchz.rick_morty.data.model.CharacterEntity
import com.synchz.rick_morty.data.model.EpisodeEntity
import com.synchz.rick_morty.data.model.LocationEntity

interface LocalDataSource {
    //    Characters
    suspend fun getCharacters(): DataSource.Factory<Int, CharacterEntity>
    suspend fun saveCharacters(listCharacters: List<CharacterEntity>)
    suspend fun saveCharactersCount(size: Int)
    suspend fun getCharactersCount(): Int
    suspend fun clearCharacters()
    //    Locations
    suspend fun getLocations(): DataSource.Factory<Int, LocationEntity>
    suspend fun saveLocations(listLocations: List<LocationEntity>)
    suspend fun saveLocationsCount(size: Int)
    suspend fun getLocationsCount(): Int
    suspend fun clearLocations()
    //    Episodes
    suspend fun getEpisodes(): DataSource.Factory<Int, EpisodeEntity>
    suspend fun saveEpisodes(listEpisodes: List<EpisodeEntity>)
    suspend fun saveEpisodeCount(size: Int)
    suspend fun getEpisodeCount(): Int
    suspend fun clearEpisodes()

}