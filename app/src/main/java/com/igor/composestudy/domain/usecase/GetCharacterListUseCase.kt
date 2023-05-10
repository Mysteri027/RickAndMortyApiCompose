package com.igor.composestudy.domain.usecase

import com.igor.composestudy.domain.model.MovieCharacter
import com.igor.composestudy.domain.repository.NetworkRepository

class GetCharacterListUseCase(
    private val networkRepository: NetworkRepository
) {

    suspend operator fun invoke(): List<MovieCharacter> = networkRepository.getCharacters()
}