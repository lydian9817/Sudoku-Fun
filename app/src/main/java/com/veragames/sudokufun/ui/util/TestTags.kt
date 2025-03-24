package com.veragames.sudokufun.ui.util

import com.veragames.sudokufun.data.model.SudokuValue
import com.veragames.sudokufun.ui.model.CellUI

object TestTags {
    const val UNDO_BUTTON = "undo_button"
    const val ERASE_BUTTON = "erase_button"
    const val PAUSE_BUTTON = "pause_button"
    const val HINT_BUTTON = "hint_button"
    const val RESUME_BUTTON = "resume_button"
    const val GO_TO_MAIN_SCREEN_BUTTON = "go_to_main_screen_button"
    const val START_GAME_BUTTON = "start_game_button"
    const val GAME_DIALOG = "game_dialog"
    const val NOTES_BUTTON = "notes_button"
    const val DIFFICULTY_INFO = "difficulty_info"
    const val TIME_INFO = "time_info"
    const val MISTAKES_INFO = "mistakes_info"

    fun getCellTestTag(cellUI: CellUI) = "cell_${cellUI.cell.row}_${cellUI.cell.col}"

    fun getSudokuValueTestTag(value: SudokuValue) = "sudoku_value_${value.name}"
}
