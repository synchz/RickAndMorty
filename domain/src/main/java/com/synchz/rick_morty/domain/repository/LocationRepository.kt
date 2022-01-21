package com.synchz.rick_morty.domain.repository

import com.synchz.rick_morty.domain.entities.Location
import kotlinx.coroutines.flow.Flow


interface LocationRepository {
    suspend fun getLocations(pageNo: Int): Flow<List<Location>>
    suspend fun getLocationById(locationId: Long): Flow<Location>
//    suspend fun saveLocations(listLocations: List<Location>)
//    suspend fun getLocationsCount(): Flow<Int>
//    suspend fun clearLocations()
}
