package com.synchz.rick_morty.data.model

data class CharacterListEntity(
    val info: InfoEntity,
    val results: List<CharacterEntity>
)