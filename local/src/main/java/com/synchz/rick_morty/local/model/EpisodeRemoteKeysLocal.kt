package com.synchz.rick_morty.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episode_remote_keys_local")
data class EpisodeRemoteKeysLocal(
    @PrimaryKey(autoGenerate = false)
    val episodeId: Long,
    val prevKey: Int?,
    val nextKey: Int?
)
