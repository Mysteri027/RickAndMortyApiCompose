package com.igor.composestudy.domain.repository

import com.igor.composestudy.domain.model.MovieCharacter

interface NetworkRepository {

    suspend fun getCharacters(): List<MovieCharacter>
}