package com.veragames.sudokufun.domain.usecases

import com.veragames.sudokufun.data.FakeBoardSupplier
import com.veragames.sudokufun.data.mockedBoard
import com.veragames.sudokufun.data.model.SudokuValue
import com.veragames.sudokufun.domain.model.BoardSize
import com.veragames.sudokufun.domain.repository.GameRepository
import com.veragames.sudokufun.domain.repository.GameRepositoryImpl
import com.veragames.sudokufun.domain.usecases.game.EraseCellValue
import com.veragames.sudokufun.domain.usecases.game.GetBoard
import com.veragames.sudokufun.domain.usecases.game.GetChronometer
import com.veragames.sudokufun.domain.usecases.game.LoadBoard
import com.veragames.sudokufun.domain.usecases.game.PauseChronometer
import com.veragames.sudokufun.domain.usecases.game.ResumeChronometer
import com.veragames.sudokufun.domain.usecases.game.SetCellValue
import com.veragames.sudokufun.domain.usecases.game.StartChronometer
import com.veragames.sudokufun.domain.usecases.game.StopChronometer
import com.veragames.sudokufun.domain.usecases.game.UndoMovement
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GameUseCasesTest {
    private lateinit var repository: GameRepository
    private lateinit var loadBoardUseCase: LoadBoard
    private lateinit var setCellValueUseCase: SetCellValue
    private lateinit var getBoardUseCase: GetBoard
    private lateinit var eraseCellValueUseCase: EraseCellValue
    private lateinit var undoMovementUseCase: UndoMovement
    private lateinit var startChronometer: StartChronometer
    private lateinit var pauseChronometer: PauseChronometer
    private lateinit var resumeChronometer: ResumeChronometer
    private lateinit var stopChronometer: StopChronometer
    private lateinit var getChronometer: GetChronometer

    @Before
    fun setUp() {
        repository = GameRepositoryImpl(FakeBoardSupplier())
        loadBoardUseCase = LoadBoard(repository)
        setCellValueUseCase = SetCellValue(repository)
        getBoardUseCase = GetBoard(repository)
        eraseCellValueUseCase = EraseCellValue(repository)
        undoMovementUseCase = UndoMovement(repository)
        startChronometer = StartChronometer(repository)
        pauseChronometer = PauseChronometer(repository)
        resumeChronometer = ResumeChronometer(repository)
        stopChronometer = StopChronometer(repository)
        getChronometer = GetChronometer(repository)
        runTest {
            loadBoardUseCase(BoardSize.NINE)
        }
    }

    @Test
    fun `set a 5 at cell 0,1`() {
        runTest {
            val cell = getBoardUseCase().value[1]
            val result = setCellValueUseCase(cell, SudokuValue.FIVE)
            assertTrue("Se debe poder setear el valor", result)
            assertEquals(SudokuValue.FIVE.value, getBoardUseCase().value[1].value)
        }
    }

    @Test
    fun `cant set value on completed cell`() {
        runTest {
            val cell = getBoardUseCase().value.first()
            val result = setCellValueUseCase(cell, SudokuValue.FIVE)
            assertFalse("No se debe poder setear el valor en una celda completada", result)
        }
    }

    @Test
    fun `cant set value on user completed cell`() {
        runTest {
            val board = getBoardUseCase()
            val cell = getBoardUseCase().value[1]
            setCellValueUseCase(cell, SudokuValue.FIVE)
            val expectedOldValue = board.value[1].value
            val result = setCellValueUseCase(cell, SudokuValue.NINE)
            assertFalse("No se debe poder setear el valor en una celda completada", result)
            assertEquals(expectedOldValue, getBoardUseCase().value[1].value)
        }
    }

    @Test
    fun `loads mocked board`() {
        runTest {
            loadBoardUseCase(BoardSize.NINE)
            val board = getBoardUseCase().value
            board.forEachIndexed { i, cell ->
                assertTrue("La fila y columna deben ser las mismas", cell.isSame(mockedBoard[i]))
                assertEquals(cell.value, mockedBoard[i].value)
            }
        }
    }

    @Test
    fun `erases user cell value`() {
        runTest {
            val cell = getBoardUseCase().value[1]
            assertTrue(setCellValueUseCase(cell, SudokuValue.FIVE))
            assertTrue(eraseCellValueUseCase(cell))
            assertEquals(SudokuValue.EMPTY.value, getBoardUseCase().value[1].value)
        }
    }

    @Test
    fun `cant erase a non user cell value`() {
        runTest {
            val cell = getBoardUseCase().value[0]
            assertFalse(eraseCellValueUseCase(cell))
        }
    }

    @Test
    fun `undoes a movement`() {
        runTest {
            val cell = getBoardUseCase().value[1]
            setCellValueUseCase(cell, SudokuValue.FOUR)
            setCellValueUseCase(cell, SudokuValue.EIGHT)
            setCellValueUseCase(cell, SudokuValue.SEVEN)
            assertEquals(SudokuValue.SEVEN.value, getBoardUseCase().value[1].value)
            undoMovementUseCase()
            assertEquals(SudokuValue.EIGHT.value, getBoardUseCase().value[1].value)
            undoMovementUseCase()
            assertEquals(SudokuValue.FOUR.value, getBoardUseCase().value[1].value)
            undoMovementUseCase()
            assertEquals(SudokuValue.EMPTY.value, getBoardUseCase().value[1].value)
        }
    }

    @Test
    fun `chronometer starts at 0`() {
        runTest {
            assertEquals(0L, getChronometer().value)
        }
    }

    @Test
    fun `chronometer starts`() {
        runTest {
            startChronometer()
            Thread.sleep(1500)
            assertTrue("El cronómetro debe ser mayor a un segundo", getChronometer().value > 1000)
        }
    }

    @Test
    fun `chronometer pauses`() {
        runTest {
            startChronometer()
            Thread.sleep(1500)
            pauseChronometer()
            Thread.sleep(2000)
            assertTrue(
                "El cronómetro debe ser menor que dos segundos",
                getChronometer().value < 2000,
            )
        }
    }

    @Test
    fun `chronometer resumes`() {
        runTest {
            startChronometer()
            Thread.sleep(1500)
            pauseChronometer()
            Thread.sleep(2000)
            assertTrue(
                "El cronómetro debe ser menor que dos segundos",
                getChronometer().value < 2000,
            )
            resumeChronometer()
            Thread.sleep(1500)
            assertTrue(
                "El cronómetro debe ser mayor que dos segundos",
                getChronometer().value > 2000,
            )
        }
    }

    @Test
    fun `chronometer stops, returns total time and resets back to 0`() {
        runTest {
            startChronometer()
            Thread.sleep(1500)
            assertTrue(
                "Stop() debe devolver un tiempo total mayor a un segundo",
                stopChronometer() > 1000,
            )
            assertEquals(0L, getChronometer().value)
        }
    }
}
