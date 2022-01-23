package com.synchz.rick_morty.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location_remote_keys_local")
data class LocationRemoteKeysLocal(
    @PrimaryKey(autoGenerate = false)
    val locationId: Long,
    val prevKey: Int?,
    val nextKey: Int?
)
