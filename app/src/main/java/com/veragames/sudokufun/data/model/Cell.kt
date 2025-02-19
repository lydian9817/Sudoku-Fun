package com.veragames.sudokufun.data.model

import com.veragames.sudokufun.domain.model.CellStatus

data class Cell(
    val value: Char,
    val row: Int,
    val col: Int,
    val box: Int,
    val status: CellStatus = CellStatus.NORMAL,
) : Comparable<Cell> {
    override fun compareTo(other: Cell): Int =
        if (row != other.row) {
            row.compareTo(other.row)
        } else {
            col.compareTo(other.col)
        }

    fun conflicts(c: Cell): Boolean = value == c.value && implicates(c) && (value != SudokuValue.EMPTY.value)

    fun implicates(c: Cell): Boolean = (row == c.row || col == c.col || box == c.box)

    override fun equals(other: Any?): Boolean =
        other is Cell && row == other.row && col == other.col && value == other.value && status == other.status

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + row
        result = 31 * result + col
        result = 31 * result + box
        result = 31 * result + status.hashCode()
        return result
    }
}
