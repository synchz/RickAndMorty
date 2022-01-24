package com.synchz.rick_morty.data.repository

import androidx.paging.DataSource
import com.synchz.rick_morty.data.dataSource.LocalDataSource
import com.synchz.rick_morty.data.dataSource.RemoteDatasource
import com.synchz.rick_morty.data.mapper.LocationMapper
import com.synchz.rick_morty.domain.entities.Location
import com.synchz.rick_morty.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocationRepositoryImpl@Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDatasource,
    private val locationMapper: LocationMapper
): LocationRepository{

    override suspend fun fetchLocationsFromServer(pageNo: Int): Flow<List<Location>> = flow {
        val locationList = remoteDataSource.getAllLocations(pageNo).let {
            if (pageNo <= 1) localDataSource.clearLocations()
            localDataSource.saveLocations(it.results)
            it.results.map { episode -> locationMapper.to(episode) }
        }
        emit(locationList)
    }

    override suspend fun getLocationsDataSource(): DataSource.Factory<Int, Location> {
        return localDataSource.getLocations().map { locationMapper.to(it) }
    }

    override suspend fun getLocationById(locationId: Long): Flow<Location> {
        return flow {
            val location = remoteDataSource.getLocationById(locationId).let {
                locationMapper.to(it)
            }
            emit(location)
        }
    }
}