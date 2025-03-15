package com.veragames.sudokufun.ui.presentation.gamescreen

import com.veragames.sudokufun.data.model.SudokuValue
import com.veragames.sudokufun.ui.model.CellUI

data class GameState(
    val board: List<CellUI> = emptyList(),
    val time: String = "00:00",
    val difficulty: String = "Easy",
    val score: Int = 0,
    val mistakes: Int = 0,
    val maxMistakes: Int = 3,
    val userValues: List<SudokuValue> = emptyList(),
    val gameRunning: Boolean = true,
)
