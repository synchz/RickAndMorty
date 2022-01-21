package com.synchz.rick_morty.remote.api

import com.synchz.rick_morty.remote.model.LocationListModel
import com.synchz.rick_morty.remote.model.LocationModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationService {

    @GET("location")
    suspend fun getAllLocations(@Query("page") page: Int): LocationListModel

    @GET("location/{id}")
    suspend fun getLocationById(@Path("id") locationId: Long): LocationModel
}