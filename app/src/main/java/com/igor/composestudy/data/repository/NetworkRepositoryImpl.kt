package com.igor.composestudy.data.repository

import com.igor.composestudy.data.mapper.CharacterResponseMapper
import com.igor.composestudy.data.network.RickAndMortyApi
import com.igor.composestudy.domain.model.MovieCharacter
import com.igor.composestudy.domain.repository.NetworkRepository

class NetworkRepositoryImpl(
    private val mapper: CharacterResponseMapper,
    private val rickAndMortyApi: RickAndMortyApi
) : NetworkRepository {

    override suspend fun getCharacters(): List<MovieCharacter> {
        val response = rickAndMortyApi.getCharacters()

        return if (response.code() == 200) {
            response.body()?.characterResponseInfo?.map {
                mapper.map(it)
            } ?: emptyList()
        } else emptyList()
    }
}
