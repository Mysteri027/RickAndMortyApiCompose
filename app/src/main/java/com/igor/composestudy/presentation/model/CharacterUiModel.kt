package com.igor.composestudy.presentation.model

import androidx.compose.ui.graphics.Color
import com.igor.composestudy.presentation.ui.theme.Alive
import com.igor.composestudy.presentation.ui.theme.Dead
import com.igor.composestudy.presentation.ui.theme.Unknown


data class CharacterUiModel(
    val name: String,
    val gender: String,
    val location: String,
    val status: CharacterStatus,
    val imageUrl: String,
)

enum class CharacterStatus {
    ALIVE, DEAD, UNKNOWN;

    fun toColor(): Color {
        return when (this) {
            ALIVE -> Alive
            DEAD -> Dead
            UNKNOWN -> Unknown
        }
    }

    override fun toString(): String {
        return when (this) {
            ALIVE -> "Alive"
            DEAD -> "Dead"
            UNKNOWN -> "Unknown"
        }
    }
}