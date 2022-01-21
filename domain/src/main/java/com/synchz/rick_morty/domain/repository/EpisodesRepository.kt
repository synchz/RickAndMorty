package com.synchz.rick_morty.domain.repository

import com.synchz.rick_morty.domain.entities.Episode
import kotlinx.coroutines.flow.Flow


interface EpisodesRepository {
    suspend fun getEpisodes(pageNo: Int): Flow<List<Episode>>
    suspend fun getEpisodeById(episodeId: Long): Flow<Episode>
//    suspend fun saveEpisodes(listEpisodes: List<Episode>)
//    suspend fun getEpisodesCount(): Flow<Int>
//    suspend fun clearEpisodes()
}
