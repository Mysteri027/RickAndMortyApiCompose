package com.igor.composestudy.data.network

import com.igor.composestudy.data.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("api/character")
    suspend fun getCharacters(): Response<CharacterResponse>

}