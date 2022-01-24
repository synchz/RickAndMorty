package com.synchz.rick_morty.di

import com.synchz.rick_morty.data.repository.CharacterRepositoryImpl
import com.synchz.rick_morty.data.repository.EpisodeRepositoryImpl
import com.synchz.rick_morty.data.repository.LocationRepositoryImpl
import com.synchz.rick_morty.domain.repository.CharacterRepository
import com.synchz.rick_morty.domain.repository.EpisodesRepository
import com.synchz.rick_morty.domain.repository.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideCharacterRepository(characterRepository: CharacterRepositoryImpl): CharacterRepository =
        characterRepository

    @Provides
    @Singleton
    fun provideLocationRepository(locationRepository: LocationRepositoryImpl): LocationRepository =
        locationRepository

    @Provides
    @Singleton
    fun provideEpisodeRepository(episodeRepository: EpisodeRepositoryImpl): EpisodesRepository =
        episodeRepository
}