package com.veragames.sudokufun.domain.usecases

import com.veragames.sudokufun.data.FakeBoardSupplier
import com.veragames.sudokufun.data.mockedBoard
import com.veragames.sudokufun.data.model.Cell
import com.veragames.sudokufun.data.model.SudokuValue
import com.veragames.sudokufun.domain.model.BoardSize
import com.veragames.sudokufun.domain.repository.GameRepository
import com.veragames.sudokufun.domain.repository.GameRepositoryImpl
import com.veragames.sudokufun.domain.usecases.game.GetBoard
import com.veragames.sudokufun.domain.usecases.game.LoadBoard
import com.veragames.sudokufun.domain.usecases.game.SetCellValue
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GameUseCasesTest {
    private lateinit var repository: GameRepository
    private lateinit var loadBoardUseCase: LoadBoard
    private lateinit var setCellValueUseCase: SetCellValue
    private lateinit var getBoardUseCase: GetBoard
    private lateinit var board: StateFlow<List<Cell>>

    @Before
    fun setUp() {
        repository = GameRepositoryImpl(FakeBoardSupplier())
        loadBoardUseCase = LoadBoard(repository)
        setCellValueUseCase = SetCellValue(repository)
        getBoardUseCase = GetBoard(repository)
        runTest {
            loadBoardUseCase.invoke(BoardSize.NINE)
        }
    }

    @Test
    fun setCellValue_5_AtCell_row0_col1() {
        runTest {
            board = getBoardUseCase()
            val cell = board.value[1]
            val result = setCellValueUseCase(cell, SudokuValue.FIVE)
            assertTrue("Se debe poder setear el valor", result)
            assertEquals(SudokuValue.FIVE.value, getBoardUseCase().value[1].value)
        }
    }

    @Test
    fun cantSetValueOnCompletedCell() {
        runTest {
            board = getBoardUseCase()
            val cell = board.value.first()
            val result = setCellValueUseCase(cell, SudokuValue.FIVE)
            assertFalse("No se debe poder setear el valor en una celda completada", result)
        }
    }

    @Test
    fun cantSetValueOnUserCompletedCell() {
        runTest {
            board = getBoardUseCase()
            val cell = board.value[1]
            setCellValueUseCase(cell, SudokuValue.FIVE)
            val expectedOldValue = board.value[1].value
            val result = setCellValueUseCase(cell, SudokuValue.NINE)
            assertFalse("No se debe poder setear el valor en una celda completada", result)
            assertEquals(expectedOldValue, getBoardUseCase().value[1].value)
        }
    }

    @Test
    fun getMockedBoard() {
        runTest {
            loadBoardUseCase(BoardSize.NINE)
            val board = getBoardUseCase().value
            board.forEachIndexed { i, cell ->
                assertTrue("La fila y columna deben ser las mismas", cell.isSame(mockedBoard[i]))
                assertEquals(cell.value, mockedBoard[i].value)
            }
        }
    }
}
