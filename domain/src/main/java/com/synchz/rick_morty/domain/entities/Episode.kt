package com.synchz.rick_morty.domain.entities

data class Episode(
    val id: Long,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
)
