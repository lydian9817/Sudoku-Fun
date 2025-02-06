package com.veragames.sudokufun.domain.model

data class Cell(
    var row: Int,
    var col: Int,
) : Comparable<Cell> {
    override fun compareTo(other: Cell): Int =
        if (row != other.row) {
            row.compareTo(other.row)
        } else {
            col.compareTo(other.col)
        }
}
