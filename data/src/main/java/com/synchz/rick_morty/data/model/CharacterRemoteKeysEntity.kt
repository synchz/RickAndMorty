package com.synchz.rick_morty.data.model

data class CharacterRemoteKeysEntity(
    val characterId: Long,
    val prevKey: Int?,
    val nextKey: Int?
)
