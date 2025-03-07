package com.veragames.sudokufun.ui.presentation.gamescreen

import com.veragames.sudokufun.ui.model.CellUI

data class GameState(
    val board: List<CellUI> = emptyList(),
)
