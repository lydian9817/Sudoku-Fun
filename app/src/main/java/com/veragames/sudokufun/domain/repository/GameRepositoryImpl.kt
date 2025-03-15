package com.veragames.sudokufun.domain.repository

import android.util.Log
import com.veragames.sudokufun.data.BoardSupplier
import com.veragames.sudokufun.data.model.Cell
import com.veragames.sudokufun.data.model.SudokuValue
import com.veragames.sudokufun.domain.model.BoardSize
import com.veragames.sudokufun.domain.util.Chronometer
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
        private val userMovements: MutableList<Cell> = mutableListOf()
        private val chronometer = Chronometer()

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
            value: Char,
        ): Boolean {
            var result = false
            board.update { currentBoard ->
                currentBoard.map { c ->
                    if (c.isSame(cell) && c.completed.not() && c.userCell) {
                        userMovements.add(c)
                        val tmpCell = cell.copy(value = value)
                        val conflicts = checkConflicts(tmpCell)
                        result = conflicts.not()
                        tmpCell.copy(value = value, conflict = conflicts, completed = conflicts.not())
                    } else {
                        c
                    }
                }
            }
            if (result) {
                Log.d(TAG, "Valor actualizado correctamente")
            } else {
                Log.d(TAG, "Conflictos encontrados o celda completada")
            }
            return result
        }

        override suspend fun eraseCellValue(cell: Cell): Boolean {
            var result = false

            board.update { currentBoard ->
                currentBoard.map { c ->
                    if (c.isSame(cell) && c.userCell && c.value != SudokuValue.EMPTY.value) {
                        userMovements.add(c)
                        result = true
                        c.copy(value = SudokuValue.EMPTY.value, conflict = false, completed = false)
                    } else {
                        c
                    }
                }
            }
            if (result) {
                Log.d(TAG, "Valor borrado correctamente")
            } else {
                Log.d(TAG, "No se pudo borrar el valor o la celda no tenia un valor")
            }
            return result
        }

        override suspend fun undoMovement(): Boolean {
            var result = false
            if (userMovements.isNotEmpty()) {
                val lastMovementCell = userMovements.removeLastOrNull()
                if (lastMovementCell != null) {
                    board.update { currentBoard ->
                        currentBoard.map { c ->
                            if (c.isSame(lastMovementCell)) {
                                result = true
                                lastMovementCell.copy(conflict = checkConflicts(lastMovementCell))
                            } else {
                                c
                            }
                        }
                    }
                }
            }
            if (result) {
                Log.d(TAG, "Movimiento deshecho correctamente")
            } else {
                Log.d(TAG, "No se pudo deshacer el movimiento")
            }
            return result
        }

        override suspend fun startChronometer() {
            chronometer.start()
        }

        override suspend fun pauseChronometer() {
            chronometer.pause()
        }

        override suspend fun resumeChronometer() {
            chronometer.resume()
        }

        override suspend fun stopChronometer() = chronometer.stop()

        override suspend fun getChronometer(): StateFlow<Long> = chronometer.getChronometer()

        private fun checkConflicts(cell: Cell): Boolean {
            board.value.forEach { c ->
                if (cell.conflicts(c)) {
                    return true
                }
            }
            return false
        }

        private companion object {
            private const val TAG = "GameRepositoryImpl"
        }
    }
