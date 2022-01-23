package com.synchz.rick_morty.remote.mapper

import com.synchz.rick_morty.data.model.EpisodeListEntity
import com.synchz.rick_morty.remote.model.EpisodeListModel
import javax.inject.Inject

class EpisodeListRemoteMapper @Inject constructor(
    private val infoRemoteMapper: InfoRemoteMapper,
    private val episodeRemoteMapper: EpisodeRemoteMapper
) : Mapper<EpisodeListModel, EpisodeListEntity> {
    override fun from(e: EpisodeListEntity) = EpisodeListModel(
        info = infoRemoteMapper.from(e.info),
        results = e.results.map { episodeRemoteMapper.from(it) }
    )

    override fun to(t: EpisodeListModel) = EpisodeListEntity (
        info = infoRemoteMapper.to(t.info),
        results = t.results.map { episodeRemoteMapper.to(it) }
    )

}