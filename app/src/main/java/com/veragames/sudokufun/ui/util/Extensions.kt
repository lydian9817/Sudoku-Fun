package com.veragames.sudokufun.ui.util

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope

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
