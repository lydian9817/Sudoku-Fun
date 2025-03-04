package com.veragames.sudokufun.data.model

import com.veragames.sudokufun.domain.model.CellStatus

data class Cell(
    val value: Char,
    val row: Int,
    val col: Int,
    val box: Int,
    val status: CellStatus = CellStatus.NORMAL,
    val completed: Boolean = false,
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
        // var result = value.hashCode()
        var result = row.hashCode()
        result = 31 * result + col
        // result = 31 * result + status.hashCode()
        // result = 31 * result + completed.hashCode()
        // result = 31 * result + box
        return result
    }
}
