package com.veragames.sudokufun.ui

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.platform.testTag
import com.veragames.sudokufun.data.model.SudokuValue
import com.veragames.sudokufun.ui.model.CellUI

fun ContentDrawScope.drawTopBorder(
    color: Color,
    sz: Float,
) {
    drawLine(
        color = color,
        start = Offset(0f, 0f),
        end = Offset(size.width, 0f),
        strokeWidth = sz,
    )
}

fun ContentDrawScope.drawBottomBorder(
    color: Color,
    sz: Float,
) {
    drawLine(
        color = color,
        start = Offset(0f, size.height),
        end = Offset(size.width, size.height),
        strokeWidth = sz,
    )
}

fun ContentDrawScope.drawLeftBorder(
    color: Color,
    sz: Float,
) {
    drawLine(
        color = color,
        start = Offset(0f, 0f),
        end = Offset(0f, size.height),
        strokeWidth = sz,
    )
}

fun ContentDrawScope.drawRightBorder(
    color: Color,
    sz: Float,
) {
    drawLine(
        color = color,
        start = Offset(size.width, 0f),
        end = Offset(size.width, size.height),
        strokeWidth = sz,
    )
}

fun ContentDrawScope.drawAllBorders(
    color: Color,
    sz: Float,
) {
    drawTopBorder(color, sz)
    drawBottomBorder(color, sz)
    drawLeftBorder(color, sz)
    drawRightBorder(color, sz)
}

fun Modifier.addCellTestTag(cellUI: CellUI) = testTag("cell_${cellUI.cell.row}_${cellUI.cell.col}_${cellUI.cell.box}")

fun Modifier.addSudokuValueTestTag(sudokuValue: SudokuValue) = testTag("value_${sudokuValue.name}")
