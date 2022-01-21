package com.synchz.rick_morty.remote.model

data class EpisodeListModel(
    val info: InfoModel,
    val results: List<EpisodeModel>
)
