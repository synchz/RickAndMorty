package com.synchz.rick_morty.local.mapper

import com.synchz.rick_morty.data.model.EpisodeEntity
import com.synchz.rick_morty.local.model.EpisodeLocal
import javax.inject.Inject

class EpisodeLocalMapper @Inject constructor() : Mapper<EpisodeLocal, EpisodeEntity> {
    override fun from(episode: EpisodeEntity) = EpisodeLocal(
        id = episode.id,
        name = episode.name,
        air_date = episode.air_date,
        episode = episode.episode,
        characters = episode.characters.joinToString(","),
        url = episode.url,
        created = episode.created
    )

    override fun to(episodeEntity: EpisodeLocal) = EpisodeEntity(
        id = episodeEntity.id,
        name = episodeEntity.name,
        air_date = episodeEntity.air_date,
        episode = episodeEntity.episode,
        characters = episodeEntity.characters.split(","),
        url = episodeEntity.url,
        created = episodeEntity.created
    )
}