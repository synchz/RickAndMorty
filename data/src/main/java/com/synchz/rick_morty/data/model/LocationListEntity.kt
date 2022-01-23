package com.synchz.rick_morty.data.model

data class LocationListEntity(
    val info: InfoEntity,
    val results: List<LocationEntity>
)