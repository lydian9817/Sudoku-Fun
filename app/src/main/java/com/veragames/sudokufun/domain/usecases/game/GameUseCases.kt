package com.veragames.sudokufun.domain.usecases.game

data class GameUseCases(
    val loadBoard: LoadBoard,
    val getBoard: GetBoard,
    val setCellValue: SetCellValue,
    val eraseCellValue: EraseCellValue,
)
