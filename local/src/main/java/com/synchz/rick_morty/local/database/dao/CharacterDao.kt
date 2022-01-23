package com.synchz.rick_morty.local.database.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.synchz.rick_morty.local.model.CharacterLocal

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character_local")
    fun getCharacters(): DataSource.Factory<Int, CharacterLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCharacters(character: CharacterLocal)

    @Query("DELETE FROM character_local")
    fun clearCharacter()
}