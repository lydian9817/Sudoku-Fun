package com.veragames.sudokufun.domain.services

import com.veragames.sudokufun.data.BoardSupplier
import com.veragames.sudokufun.data.model.Cell
import com.veragames.sudokufun.data.model.SudokuValue
import com.veragames.sudokufun.domain.model.BoardSize
import com.veragames.sudokufun.domain.model.CellStatus
import com.veragames.sudokufun.domain.usecases.GameUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class GameService
    @Inject
    constructor(
        private val gameBoardSupplier: BoardSupplier,
    ) : GameUseCases {
        private val board: MutableStateFlow<List<Cell>> = MutableStateFlow(emptyList())
        private val userConflicts: MutableSet<Cell> = mutableSetOf()
        private lateinit var selectedCell: Cell

        override suspend fun loadBoard(size: BoardSize) {
            board.update { gameBoardSupplier.getBoard(size.size).first() }
            selectCell(board.value.find { it.value == SudokuValue.EMPTY.value }!!)
        }

        override suspend fun getBoard(): StateFlow<List<Cell>> = board.asStateFlow()

        override suspend fun setCellValue(value: SudokuValue) {
            if (selectedCell.completed.not()) {
                board.update { currentBoard ->
                    currentBoard.map { c ->
                        if (c.row == selectedCell.row && c.col == selectedCell.col) {
                            selectedCell = selectedCell.copy(value = value.value)
                            c.copy(value = value.value)
                        } else {
                            c
                        }
                    }
                }
                updateStatus()
            }
        }

        override fun selectCell(cell: Cell) {
            board.update { currentBoard ->
                currentBoard.map { c ->
                    when {
                        (c.row == cell.row && c.col == cell.col) -> c.copy(status = CellStatus.SELECTED)
                        ::selectedCell.isInitialized && c.status == CellStatus.SELECTED -> c.copy(status = CellStatus.NORMAL)
                        else -> c
                    }
                }
            }
            selectedCell = cell
            updateStatus()
        }

        private fun updateStatus() {
            board.update { currentBoard ->
                currentBoard.map { cell ->
                    cell.copy(
                        status =
                            when {
                                cell.status == CellStatus.SELECTED -> CellStatus.SELECTED
                                selectedCell.conflicts(cell) -> {
                                    // userConflicts.add(selectedCell)
                                    CellStatus.CONFLICT
                                }
                                selectedCell.implicates(cell) -> CellStatus.IMPLICATED
                                selectedCell.value == cell.value && selectedCell.value != SudokuValue.EMPTY.value -> {
                                    CellStatus.COMMON_NUMBER
                                }

                                else -> CellStatus.NORMAL
                            },
                    )
                }
            }
        }
    }
