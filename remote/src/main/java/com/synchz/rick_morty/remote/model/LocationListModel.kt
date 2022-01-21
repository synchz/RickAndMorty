package com.synchz.rick_morty.remote.model

data class LocationListModel(
    val info: InfoModel,
    val results: List<LocationModel>
)
