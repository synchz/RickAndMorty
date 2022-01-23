package com.synchz.rick_morty.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_remote_keys_local")
data class CharacterRemoteKeysLocal(
    @PrimaryKey(autoGenerate = false)
    val characterId: Long,
    val prevKey: Int?,
    val nextKey: Int?
)
