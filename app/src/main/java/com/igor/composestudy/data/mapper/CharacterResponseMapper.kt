package com.igor.composestudy.data.mapper

import com.igor.composestudy.data.model.CharacterResponseInfo
import com.igor.composestudy.domain.mapper.base.Mapper
import com.igor.composestudy.domain.model.MovieCharacter

class CharacterResponseMapper : Mapper<CharacterResponseInfo, MovieCharacter>() {

    override fun map(from: CharacterResponseInfo): MovieCharacter = from.run {
        MovieCharacter(
            id = this.id,
            name = this.name,
            gender = this.gender,
            location = this.location.name,
            status = this.status,
            imageUrl = this.image
        )
    }
}