package com.synchz.rick_morty.remote.model

data class CharacterModel(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: CharacterLocationModel,
    val location: CharacterLocationModel,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

data class CharacterLocationModel(
    val name: String,
    val url: String
)
