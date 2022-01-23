package com.synchz.rick_morty.di

import android.content.Context
import com.synchz.rick_morty.data.dataSource.LocalDataSource
import com.synchz.rick_morty.local.database.RickAndMortyDB
import com.synchz.rick_morty.local.database.dao.CharacterDao
import com.synchz.rick_morty.local.database.dao.EpisodeDao
import com.synchz.rick_morty.local.database.dao.LocationDao
import com.synchz.rick_morty.local.source.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): RickAndMortyDB {
        return RickAndMortyDB.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideCharacterDao(rickAndMortyDB: RickAndMortyDB): CharacterDao {
        return rickAndMortyDB.getCharacterDao()
    }

    @Provides
    @Singleton
    fun provideLocationDao(rickAndMortyDB: RickAndMortyDB): LocationDao {
        return rickAndMortyDB.getLocationDao()
    }

    @Provides
    @Singleton
    fun provideEpisodeDao(rickAndMortyDB: RickAndMortyDB): EpisodeDao {
        return rickAndMortyDB.getEpisodeDao()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(localDataSource: LocalDataSourceImpl): LocalDataSource {
        return localDataSource
    }

}