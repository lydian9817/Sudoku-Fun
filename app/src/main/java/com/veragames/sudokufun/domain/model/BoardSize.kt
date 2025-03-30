package com.veragames.sudokufun.domain.model

import com.veragames.sudokufun.data.model.SudokuValue
import com.veragames.sudokufun.data.model.SudokuValue.A
import com.veragames.sudokufun.data.model.SudokuValue.B
import com.veragames.sudokufun.data.model.SudokuValue.C
import com.veragames.sudokufun.data.model.SudokuValue.D
import com.veragames.sudokufun.data.model.SudokuValue.E
import com.veragames.sudokufun.data.model.SudokuValue.EIGHT
import com.veragames.sudokufun.data.model.SudokuValue.F
import com.veragames.sudokufun.data.model.SudokuValue.FIVE
import com.veragames.sudokufun.data.model.SudokuValue.FOUR
import com.veragames.sudokufun.data.model.SudokuValue.G
import com.veragames.sudokufun.data.model.SudokuValue.NINE
import com.veragames.sudokufun.data.model.SudokuValue.ONE
import com.veragames.sudokufun.data.model.SudokuValue.SEVEN
import com.veragames.sudokufun.data.model.SudokuValue.SIX
import com.veragames.sudokufun.data.model.SudokuValue.THREE
import com.veragames.sudokufun.data.model.SudokuValue.TWO

private val NINE_VALUES = listOf(ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE)
private val SIXTEEN_VALUES =
    listOf(ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, A, B, C, D, E, F, G)

enum class BoardSize(
    val size: Int,
    val values: List<SudokuValue>,
) {
    NINE(9, NINE_VALUES),
    SIXTEEN(16, SIXTEEN_VALUES),
}
