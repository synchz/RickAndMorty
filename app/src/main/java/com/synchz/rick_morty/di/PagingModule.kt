package com.synchz.rick_morty.di

import androidx.paging.ExperimentalPagingApi
import com.synchz.rick_morty.data.dataSource.LocalDataSource
import com.synchz.rick_morty.data.dataSource.PagingDataSource
import com.synchz.rick_morty.data.dataSource.RemoteDatasource
import com.synchz.rick_morty.paging.mediators.CharacterListRemoteMediator
import com.synchz.rick_morty.paging.source.PagingDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PagingModule {

    @ExperimentalPagingApi
    @Provides
    @Singleton
    fun providePagingDataSource(pagingDataSource: PagingDataSourceImpl): PagingDataSource {
        return pagingDataSource
    }

    @ExperimentalPagingApi
    @Provides
    @Singleton
    fun provideCharacterRemoteMediator(localDataSource: LocalDataSource, remoteDatasource: RemoteDatasource): CharacterListRemoteMediator {
        return CharacterListRemoteMediator(localDataSource = localDataSource, remoteDatasource = remoteDatasource)
    }

}