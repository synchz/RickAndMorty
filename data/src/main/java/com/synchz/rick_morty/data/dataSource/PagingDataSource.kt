package com.synchz.rick_morty.data.dataSource

import androidx.paging.PagingData
import com.synchz.rick_morty.data.model.CharacterEntity
import com.synchz.rick_morty.data.model.EpisodeEntity
import com.synchz.rick_morty.data.model.LocationEntity
import kotlinx.coroutines.flow.Flow

interface PagingDataSource {
    //    Characters
    suspend fun getCharacters(): Flow<PagingData<CharacterEntity>>
    //    Locations
    suspend fun getLocations(): Flow<PagingData<LocationEntity>>
    //    Episodes
    suspend fun getEpisodes(): Flow<PagingData<EpisodeEntity>>

}