package com.synchz.rick_morty.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location_local")
data class LocationLocal(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: String,
    val url: String,
    val created: String
)
