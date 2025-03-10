package com.veragames.sudokufun.data.model

data class Cell(
    val value: Char,
    val row: Int,
    val col: Int,
    val box: Int,
    val userCell: Boolean = true,
    val completed: Boolean = false,
    val conflict: Boolean = false,
) : Comparable<Cell> {
    override fun compareTo(other: Cell): Int =
        if (row != other.row) {
            row.compareTo(other.row)
        } else {
            col.compareTo(other.col)
        }

    fun conflicts(c: Cell): Boolean = value == c.value && implicates(c) && (value != SudokuValue.EMPTY.value) && this.isSame(c).not()

    fun implicates(c: Cell): Boolean = (row == c.row || col == c.col || box == c.box)

    fun isSame(c: Cell): Boolean = row == c.row && col == c.col
}
