package com.igor.composestudy.presentation.mapper

import com.igor.composestudy.domain.mapper.base.Mapper
import com.igor.composestudy.domain.model.MovieCharacter
import com.igor.composestudy.presentation.model.CharacterStatus
import com.igor.composestudy.presentation.model.CharacterUiModel

class CharacterUiMapper : Mapper<MovieCharacter, CharacterUiModel>() {
    override fun map(from: MovieCharacter) = from.run {
        CharacterUiModel(
            name = this.name,
            gender = this.gender,
            location = this.location,
            status = getCharacterStatus(this.status),
            imageUrl = this.imageUrl
        )
    }
}

fun getCharacterStatus(status: String): CharacterStatus {
    return when (status) {
        "Alive" -> CharacterStatus.ALIVE
        "Dead" -> CharacterStatus.DEAD
        else -> CharacterStatus.UNKNOWN
    }
}