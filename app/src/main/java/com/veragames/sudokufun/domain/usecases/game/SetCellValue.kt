package com.veragames.sudokufun.domain.usecases.game

import com.veragames.sudokufun.data.model.Cell
import com.veragames.sudokufun.data.model.SudokuValue
import com.veragames.sudokufun.domain.repository.GameRepository
import javax.inject.Inject

class SetCellValue
    @Inject
    constructor(
        private val gameRepository: GameRepository,
    ) {
        suspend operator fun invoke(
            cell: Cell,
            value: SudokuValue,
        ) {
            gameRepository.setCellValue(cell, value)
        }
    }
