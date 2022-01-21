package com.synchz.rick_morty.remote.model

data class CharacterListModel(
    val info: InfoModel,
    val results: List<CharacterModel>
)
