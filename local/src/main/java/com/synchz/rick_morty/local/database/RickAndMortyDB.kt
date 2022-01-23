package com.synchz.rick_morty.local.database

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.synchz.rick_morty.local.database.dao.CharacterDao
import com.synchz.rick_morty.local.database.dao.EpisodeDao
import com.synchz.rick_morty.local.database.dao.LocationDao
import com.synchz.rick_morty.local.model.*

@Database(
    entities = [CharacterLocal::class, EpisodeLocal::class, LocationLocal::class, EpisodeRemoteKeysLocal::class, CharacterRemoteKeysLocal::class, LocationRemoteKeysLocal::class],
    version = 1,
    exportSchema = false
)
abstract class RickAndMortyDB : RoomDatabase() {

    companion object {
        private val LOCK = Any()
        private const val DATABASE_NAME = "rick_and_morty.db"

        @Volatile
        private var INSTANCE: RickAndMortyDB? = null

        fun getInstance(@NonNull context: Context): RickAndMortyDB {
            if (INSTANCE == null) {
                synchronized(LOCK) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            RickAndMortyDB::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    abstract fun getCharacterDao(): CharacterDao
    abstract fun getEpisodeDao(): EpisodeDao
    abstract fun getLocationDao(): LocationDao
}