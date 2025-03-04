package com.veragames.sudokufun.ui.presentation.gamescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veragames.sudokufun.data.model.Cell
import com.veragames.sudokufun.data.model.SudokuValue
import com.veragames.sudokufun.domain.model.BoardSize
import com.veragames.sudokufun.domain.usecases.GameUseCases
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
        private val _state = MutableStateFlow(GameState())
        val state: StateFlow<GameState> = _state.asStateFlow()

        init {
            viewModelScope.launch {
                gameUseCases.loadBoard(BoardSize.NINE)
                gameUseCases.getBoard().collect { board ->
                    _state.update {
                        it.copy(
                            board = board,
                        )
                    }
                }
            }
        }

        fun onCellClicked(cell: Cell) {
            viewModelScope.launch {
                gameUseCases.selectCell(cell)
            }
        }

        fun setCellValue(value: SudokuValue) {
            viewModelScope.launch {
                gameUseCases.setCellValue(value)
            }
        }
    }
