package com.veragames.sudokufun.domain.repository

import com.veragames.sudokufun.data.BoardSupplier
import com.veragames.sudokufun.data.model.Cell
import com.veragames.sudokufun.data.model.SudokuValue
import com.veragames.sudokufun.domain.model.BoardSize
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class GameRepositoryImpl
    @Inject
    constructor(
        private val gameBoardSupplier: BoardSupplier,
    ) : GameRepository {
        private val board: MutableStateFlow<List<Cell>> = MutableStateFlow(emptyList())
        private val userConflicts: MutableSet<Cell> = mutableSetOf()

        override suspend fun loadBoard(size: BoardSize) {
            board.update {
                gameBoardSupplier.getBoard(size.size).first().map {
                    if (it.value == SudokuValue.EMPTY.value) {
                        it
                    } else {
                        it.copy(userCell = false)
                    }
                }
            }
        }

        override suspend fun getBoard(): StateFlow<List<Cell>> = board.asStateFlow()

        override suspend fun setCellValue(
            cell: Cell,
            value: SudokuValue,
        ) {
            board.update { currentBoard ->
                currentBoard.map { c ->
                    if (c.isSame(cell) && c.completed.not() && c.userCell) {
                        val tmpCell = cell.copy(value = value.value)
                        val conflicts = checkConflicts(tmpCell)
                        tmpCell.copy(value = value.value, conflict = conflicts, completed = conflicts.not())
                    } else {
                        c
                    }
                }
            }
        }

        private fun checkConflicts(cell: Cell): Boolean {
            board.value.forEach { c ->
                if (cell.conflicts(c)) {
                    // userConflicts.add(cell)
                    return true
                }
            }
            return false
        }
    }
