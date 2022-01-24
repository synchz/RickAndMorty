package com.synchz.rick_morty.domain.repository

import androidx.paging.DataSource
import com.synchz.rick_morty.domain.entities.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {
    suspend fun fetchEpisodesFromServer(pageNo: Int): Flow<List<Episode>>
    suspend fun getEpisodesDataSource(): DataSource.Factory<Int, Episode>
    suspend fun getEpisodeById(episodeId: Long): Flow<Episode>
}
