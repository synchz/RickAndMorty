package com.synchz.rick_morty.local.database.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.synchz.rick_morty.local.model.EpisodeLocal

@Dao
interface EpisodeDao{

    @Query("SELECT * FROM episode_local")
    fun getEpisodes(): DataSource.Factory<Int, EpisodeLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveEpisodes(character: EpisodeLocal)

    @Query("DELETE FROM episode_local")
    fun clearEpisodes()
}