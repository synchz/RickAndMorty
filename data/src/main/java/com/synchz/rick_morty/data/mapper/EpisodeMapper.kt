package com.synchz.rick_morty.data.mapper

import com.synchz.rick_morty.data.model.EpisodeEntity
import com.synchz.rick_morty.domain.entities.Episode

class EpisodeMapper : Mapper<EpisodeEntity, Episode> {
    override fun from(episode: Episode) = EpisodeEntity(
        id = episode.id,
        name = episode.name,
        air_date = episode.air_date,
        episode = episode.episode,
        characters = episode.characters,
        url = episode.url,
        created = episode.created
    )

    override fun to(episodeEntity: EpisodeEntity) = Episode(
        id = episodeEntity.id,
        name = episodeEntity.name,
        air_date = episodeEntity.air_date,
        episode = episodeEntity.episode,
        characters = episodeEntity.characters,
        url = episodeEntity.url,
        created = episodeEntity.created
    )
}