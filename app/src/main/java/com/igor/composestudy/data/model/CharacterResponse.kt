package com.igor.composestudy.data.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    val info: Info,
    @SerializedName("results") val characterResponseInfo: List<CharacterResponseInfo>
)