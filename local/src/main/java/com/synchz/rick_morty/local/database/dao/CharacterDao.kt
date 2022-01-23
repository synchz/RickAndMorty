package com.synchz.rick_morty.local.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.synchz.rick_morty.local.model.CharacterLocal
import com.synchz.rick_morty.local.model.CharacterRemoteKeysLocal

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character_local")
    fun getCharacters(): PagingSource<Int, CharacterLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCharacters(character: CharacterLocal)

    @Query("DELETE FROM character_local")
    fun clearCharacter(): Int

    @Query("SELECT * FROM character_remote_keys_local where characterId =:characterId")
    fun getCharacterRemoteKeyById(characterId: Long): CharacterRemoteKeysLocal?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCharacterRemoteKey(characterRemoteKey: CharacterRemoteKeysLocal)

    @Query("DELETE FROM character_remote_keys_local")
    fun clearCharacterRemoteKey(): Int
}