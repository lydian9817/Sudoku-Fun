package com.veragames.sudokufun.domain.usecases

import com.veragames.sudokufun.data.model.Cell
import com.veragames.sudokufun.data.model.SudokuValue
import com.veragames.sudokufun.domain.model.BoardSize
import kotlinx.coroutines.flow.StateFlow

interface GameUseCases {
    suspend fun loadBoard(size: BoardSize)

    suspend fun getBoard(): StateFlow<List<Cell>>

    suspend fun setCellValue(cell: Cell, value: SudokuValue)
}
