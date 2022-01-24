package com.synchz.rick_morty.data.repository

import androidx.paging.DataSource
import com.synchz.rick_morty.data.dataSource.LocalDataSource
import com.synchz.rick_morty.data.dataSource.RemoteDatasource
import com.synchz.rick_morty.data.mapper.EpisodeMapper
import com.synchz.rick_morty.domain.entities.Episode
import com.synchz.rick_morty.domain.repository.EpisodesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EpisodeRepositoryImpl@Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDatasource,
    private val episodeMapper: EpisodeMapper
): EpisodesRepository{
    override suspend fun fetchEpisodesFromServer(pageNo: Int): Flow<List<Episode>> = flow {
        val episodeList = remoteDataSource.getEpisodes(pageNo).let {
            if (pageNo <= 1) localDataSource.clearEpisodes()
            localDataSource.saveEpisodes(it.results)
            it.results.map { episode -> episodeMapper.to(episode) }
        }
        emit(episodeList)
    }

    override suspend fun getEpisodesDataSource(): DataSource.Factory<Int, Episode> {
        return localDataSource.getEpisodes().map { episodeMapper.to(it) }
    }

    override suspend fun getEpisodeById(episodeId: Long): Flow<Episode> {
        return flow {
            val episode = remoteDataSource.getEpisodeById(episodeId).let {
                episodeMapper.to(it)
            }
            emit(episode)
        }
    }
}