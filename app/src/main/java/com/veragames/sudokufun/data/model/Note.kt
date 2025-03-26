package com.veragames.sudokufun.data.model

data class Note(
    val value: SudokuValue = SudokuValue.EMPTY,
    val noted: Boolean = false,
)
