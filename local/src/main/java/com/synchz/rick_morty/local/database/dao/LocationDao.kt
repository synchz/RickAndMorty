package com.synchz.rick_morty.local.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.synchz.rick_morty.local.model.LocationLocal

@Dao
interface LocationDao {

    @Query("SELECT * FROM location_local")
    fun getLocations(): PagingSource<Int, LocationLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveLocation(character: LocationLocal)

    @Query("DELETE FROM location_local")
    fun clearLocations(): Int
}