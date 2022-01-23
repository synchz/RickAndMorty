package com.synchz.rick_morty.data.model

data class EpisodeListEntity(
    val info: InfoEntity,
    val results: List<EpisodeEntity>
)