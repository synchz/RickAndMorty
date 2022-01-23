package com.synchz.rick_morty.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episode_local")
data class EpisodeLocal(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: String,
    val url: String,
    val created: String
)
