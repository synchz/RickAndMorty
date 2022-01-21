package com.synchz.rick_morty.remote.api

import com.synchz.rick_morty.remote.model.CharacterListModel
import com.synchz.rick_morty.remote.model.CharacterModel
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {

    @GET("character")
    suspend fun getAllCharacters(): CharacterListModel

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") characterId: Long): CharacterModel
}