package com.veragames.sudokufun

import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.veragames.sudokufun.data.FakeBoardSupplier
import com.veragames.sudokufun.domain.repository.GameRepository
import com.veragames.sudokufun.domain.repository.GameRepositoryImpl
import com.veragames.sudokufun.domain.usecases.game.EraseCellValue
import com.veragames.sudokufun.domain.usecases.game.GameUseCases
import com.veragames.sudokufun.domain.usecases.game.GetBoard
import com.veragames.sudokufun.domain.usecases.game.LoadBoard
import com.veragames.sudokufun.domain.usecases.game.SetCellValue
import com.veragames.sudokufun.ui.presentation.gamescreen.GameScreen
import com.veragames.sudokufun.ui.presentation.gamescreen.GameViewModel
import com.veragames.sudokufun.ui.theme.SudokuFunTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BoardTest {
    private lateinit var repository: GameRepository
    private lateinit var loadBoardUseCase: LoadBoard
    private lateinit var setCellValueUseCase: SetCellValue
    private lateinit var getBoardUseCase: GetBoard
    private lateinit var eraseCellValueUseCase: EraseCellValue
    private lateinit var gameViewModel: GameViewModel

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        repository = GameRepositoryImpl(FakeBoardSupplier())
        loadBoardUseCase = LoadBoard(repository)
        setCellValueUseCase = SetCellValue(repository)
        getBoardUseCase = GetBoard(repository)
        eraseCellValueUseCase = EraseCellValue(repository)
        gameViewModel =
            GameViewModel(GameUseCases(loadBoardUseCase, getBoardUseCase, setCellValueUseCase, eraseCellValueUseCase))

        composeTestRule.setContent {
            SudokuFunTheme {
                GameScreen(gameViewModel)
            }
        }
    }

    @Test
    fun loads_board_on_screen() {
        gameViewModel.state.value.board.forEach {
            composeTestRule
                .onNodeWithTag("cell_${it.cell.row}_${it.cell.col}_${it.cell.box}")
                .assertExists("La celda ${it.cell.row}, ${it.cell.col} no se muestra")
        }
    }

    @Test
    fun sets_value_on_board() {
        composeTestRule.onNodeWithTag("cell_3_4_4").performClick()
        composeTestRule.onNodeWithTag("value_FIVE").performClick()
        composeTestRule.onNodeWithTag("cell_3_4_4").assertTextContains("5")
    }

    @Test
    fun erases_value_on_board() {
        sets_value_on_board()
        composeTestRule.onNodeWithTag("cell_3_4_4").performClick()
        composeTestRule.onNodeWithTag("erase_button").performClick()
        composeTestRule.onNodeWithTag("cell_3_4_4").assertTextContains(" ")
    }
}
