package com.veragames.sudokufun.ui.presentation.gamescreen

import com.veragames.sudokufun.data.model.Cell

data class GameState(
    val board: List<Cell> = emptyList(),
)
