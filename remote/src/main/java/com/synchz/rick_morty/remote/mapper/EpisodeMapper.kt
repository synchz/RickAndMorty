package com.synchz.rick_morty.remote.mapper

import com.synchz.rick_morty.data.model.EpisodeEntity
import com.synchz.rick_morty.remote.model.EpisodeModel

class EpisodeMapper : Mapper<EpisodeModel, EpisodeEntity> {
    override fun from(episode: EpisodeEntity) = EpisodeModel(
        id = episode.id,
        name = episode.name,
        air_date = episode.air_date,
        episode = episode.episode,
        characters = episode.characters,
        url = episode.url,
        created = episode.created
    )

    override fun to(episodeEntity: EpisodeModel) = EpisodeEntity(
        id = episodeEntity.id,
        name = episodeEntity.name,
        air_date = episodeEntity.air_date,
        episode = episodeEntity.episode,
        characters = episodeEntity.characters,
        url = episodeEntity.url,
        created = episodeEntity.created
    )
}