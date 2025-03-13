package com.veragames.sudokufun.ui.presentation.gamescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veragames.sudokufun.data.model.SudokuValue
import com.veragames.sudokufun.domain.model.BoardSize
import com.veragames.sudokufun.domain.model.CellStatus
import com.veragames.sudokufun.domain.usecases.game.GameUseCases
import com.veragames.sudokufun.ui.model.CellUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel
    @Inject
    constructor(
        private val gameUseCases: GameUseCases,
    ) : ViewModel() {
        private lateinit var selectedCell: CellUI
        private val initiated = MutableStateFlow(false)
        private val _state = MutableStateFlow(GameState())
        val state: StateFlow<GameState> = _state.asStateFlow()

        init {
            viewModelScope.launch {
                gameUseCases.loadBoard(BoardSize.NINE)
                gameUseCases.getBoard().collect { board ->
                    if (initiated.value.not()) {
                        _state.update {
                            it.copy(
                                board =
                                    board.map { cell ->
                                        CellUI(cell)
                                    },
                            )
                        }
                        selectCell(_state.value.board.find { cellUI -> cellUI.cell.value == SudokuValue.EMPTY.value }!!)
                        initiated.value = true
                    } else {
                        _state.update {
                            it.copy(
                                board =
                                    it.board.mapIndexed { index, cUI ->
                                        if (board[index].isSame(selectedCell.cell)) {
                                            selectedCell = selectedCell.copy(cell = board[index])
                                        }
                                        cUI.copy(cell = board[index])
                                    },
                            )
                        }
                        updateStatus()
                    }
                }
            }
        }

        fun selectCell(cellUI: CellUI) {
            viewModelScope.launch {
                _state.update {
                    it.copy(
                        board =
                            it.board.map { cUI ->
                                when {
                                    cellUI.cell.isSame(cUI.cell) -> {
                                        selectedCell = cUI
                                        cUI.copy(status = CellStatus.SELECTED)
                                    }

                                    cUI.status == CellStatus.SELECTED -> cUI.copy(status = CellStatus.NORMAL)
                                    else -> cUI
                                }
                            },
                    )
                }
                updateStatus()
            }
        }

        fun setCellValue(value: SudokuValue) {
            viewModelScope.launch {
                gameUseCases.setCellValue(selectedCell.cell, value)
            }
        }

        fun eraseCellValue() {
            viewModelScope.launch {
                gameUseCases.eraseCellValue(selectedCell.cell)
            }
        }

        private fun updateStatus() {
            _state.update {
                it.copy(
                    board =
                        it.board.map { cUI ->
                            cUI.copy(
                                status =
                                    when {
                                        cUI.status == CellStatus.SELECTED -> CellStatus.SELECTED
                                        selectedCell.cell.conflicts(cUI.cell) -> CellStatus.CONFLICT

                                        selectedCell.cell.implicates(cUI.cell) -> CellStatus.IMPLICATED
                                        selectedCell.cell.value == cUI.cell.value && selectedCell.cell.value != SudokuValue.EMPTY.value -> {
                                            CellStatus.COMMON_NUMBER
                                        }

                                        else -> CellStatus.NORMAL
                                    },
                            )
                        },
                )
            }
        }
    }
