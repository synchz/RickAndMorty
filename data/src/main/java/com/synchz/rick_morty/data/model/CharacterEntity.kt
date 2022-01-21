package com.synchz.rick_morty.data.model

data class CharacterEntity(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: CharacterLocationEntity,
    val location: CharacterLocationEntity,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

data class CharacterLocationEntity(
    val name: String,
    val url: String
)
