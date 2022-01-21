package com.synchz.rick_morty.remote.api

import com.synchz.rick_morty.remote.model.LocationListModel
import com.synchz.rick_morty.remote.model.LocationModel
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationService {

    @GET("location")
    suspend fun getAllLocations(): LocationListModel

    @GET("location/{id}")
    suspend fun getLocationById(@Path("id") locationId: Long): LocationModel
}