package com.synchz.rick_morty.domain.repository

import androidx.paging.DataSource
import com.synchz.rick_morty.domain.entities.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun fetchLocationsFromServer(pageNo: Int): Flow<List<Location>>
    suspend fun getLocationsDataSource(): DataSource.Factory<Int, Location>
    suspend fun getLocationById(locationId: Long): Flow<Location>
}
