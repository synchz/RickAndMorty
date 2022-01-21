package com.synchz.rick_morty.remote.api

import com.synchz.rick_morty.remote.model.EpisodeListModel
import com.synchz.rick_morty.remote.model.EpisodeModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodeService {

    @GET("episode")
    suspend fun getAllEpisodes(@Query("page") page: Int): EpisodeListModel

    @GET("episode/{id}")
    suspend fun getEpisodeById(@Path("id") episodeId: Long): EpisodeModel
}