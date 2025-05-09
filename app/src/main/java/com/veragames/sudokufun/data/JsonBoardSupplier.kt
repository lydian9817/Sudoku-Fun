package com.veragames.sudokufun.data

import android.content.Context
import com.veragames.sudokufun.data.model.Cell
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

val mockedBoard =
    listOf(
        Cell(value = '9', row = 0, col = 0, box = 0, completed = true, userCell = false),
        Cell(value = ' ', row = 0, col = 1, box = 0, completed = false),
        Cell(value = ' ', row = 0, col = 2, box = 0, completed = false),
        Cell(value = '4', row = 0, col = 3, box = 1, completed = true, userCell = false),
        Cell(value = '8', row = 0, col = 4, box = 1, completed = true, userCell = false),
        Cell(value = '7', row = 0, col = 5, box = 1, completed = true, userCell = false),
        Cell(value = '3', row = 0, col = 6, box = 2, completed = true, userCell = false),
        Cell(value = ' ', row = 0, col = 7, box = 2, completed = false),
        Cell(value = ' ', row = 0, col = 8, box = 2, completed = false),
        Cell(value = ' ', row = 1, col = 0, box = 0, completed = false),
        Cell(value = ' ', row = 1, col = 1, box = 0, completed = false),
        Cell(value = ' ', row = 1, col = 2, box = 0, completed = false),
        Cell(value = '2', row = 1, col = 3, box = 1, completed = true, userCell = false),
        Cell(value = '3', row = 1, col = 4, box = 1, completed = true, userCell = false),
        Cell(value = ' ', row = 1, col = 5, box = 1, completed = false),
        Cell(value = ' ', row = 1, col = 6, box = 2, completed = false),
        Cell(value = ' ', row = 1, col = 7, box = 2, completed = false),
        Cell(value = ' ', row = 1, col = 8, box = 2, completed = false),
        Cell(value = '3', row = 2, col = 0, box = 0, completed = true, userCell = false),
        Cell(value = ' ', row = 2, col = 1, box = 0, completed = false),
        Cell(value = ' ', row = 2, col = 2, box = 0, completed = false),
        Cell(value = ' ', row = 2, col = 3, box = 1, completed = false),
        Cell(value = '6', row = 2, col = 4, box = 1, completed = true, userCell = false),
        Cell(value = ' ', row = 2, col = 5, box = 1, completed = false),
        Cell(value = ' ', row = 2, col = 6, box = 2, completed = false),
        Cell(value = '7', row = 2, col = 7, box = 2, completed = true, userCell = false),
        Cell(value = ' ', row = 2, col = 8, box = 2, completed = false),
        Cell(value = ' ', row = 3, col = 0, box = 3, completed = false),
        Cell(value = '9', row = 3, col = 1, box = 3, completed = true, userCell = false),
        Cell(value = ' ', row = 3, col = 2, box = 3, completed = false),
        Cell(value = '3', row = 3, col = 3, box = 4, completed = true, userCell = false),
        Cell(value = ' ', row = 3, col = 4, box = 4, completed = false),
        Cell(value = ' ', row = 3, col = 5, box = 4, completed = false),
        Cell(value = '1', row = 3, col = 6, box = 5, completed = true, userCell = false),
        Cell(value = ' ', row = 3, col = 7, box = 5, completed = false),
        Cell(value = '7', row = 3, col = 8, box = 5, completed = true, userCell = false),
        Cell(value = '6', row = 4, col = 0, box = 3, completed = true, userCell = false),
        Cell(value = ' ', row = 4, col = 1, box = 3, completed = false),
        Cell(value = ' ', row = 4, col = 2, box = 3, completed = false),
        Cell(value = '1', row = 4, col = 3, box = 4, completed = true, userCell = false),
        Cell(value = '7', row = 4, col = 4, box = 4, completed = true, userCell = false),
        Cell(value = '9', row = 4, col = 5, box = 4, completed = true, userCell = false),
        Cell(value = ' ', row = 4, col = 6, box = 5, completed = false),
        Cell(value = '3', row = 4, col = 7, box = 5, completed = true, userCell = false),
        Cell(value = '4', row = 4, col = 8, box = 5, completed = true, userCell = false),
        Cell(value = '1', row = 5, col = 0, box = 3, completed = true, userCell = false),
        Cell(value = ' ', row = 5, col = 1, box = 3, completed = false),
        Cell(value = ' ', row = 5, col = 2, box = 3, completed = false),
        Cell(value = ' ', row = 5, col = 3, box = 4, completed = false),
        Cell(value = ' ', row = 5, col = 4, box = 4, completed = false),
        Cell(value = ' ', row = 5, col = 5, box = 4, completed = false),
        Cell(value = ' ', row = 5, col = 6, box = 5, completed = false),
        Cell(value = '5', row = 5, col = 7, box = 5, completed = true, userCell = false),
        Cell(value = '8', row = 5, col = 8, box = 5, completed = true, userCell = false),
        Cell(value = ' ', row = 6, col = 0, box = 6, completed = false),
        Cell(value = ' ', row = 6, col = 1, box = 6, completed = false),
        Cell(value = '1', row = 6, col = 2, box = 6, completed = true, userCell = false),
        Cell(value = '7', row = 6, col = 3, box = 7, completed = true, userCell = false),
        Cell(value = ' ', row = 6, col = 4, box = 7, completed = false),
        Cell(value = '4', row = 6, col = 5, box = 7, completed = true, userCell = false),
        Cell(value = ' ', row = 6, col = 6, box = 8, completed = false),
        Cell(value = ' ', row = 6, col = 7, box = 8, completed = false),
        Cell(value = '3', row = 6, col = 8, box = 8, completed = true, userCell = false),
        Cell(value = ' ', row = 7, col = 0, box = 6, completed = false),
        Cell(value = ' ', row = 7, col = 1, box = 6, completed = false),
        Cell(value = '3', row = 7, col = 2, box = 6, completed = true, userCell = false),
        Cell(value = '8', row = 7, col = 3, box = 7, completed = true, userCell = false),
        Cell(value = ' ', row = 7, col = 4, box = 7, completed = false),
        Cell(value = '6', row = 7, col = 5, box = 7, completed = true, userCell = false),
        Cell(value = '7', row = 7, col = 6, box = 8, completed = true, userCell = false),
        Cell(value = ' ', row = 7, col = 7, box = 8, completed = false),
        Cell(value = '2', row = 7, col = 8, box = 8, completed = true, userCell = false),
        Cell(value = ' ', row = 8, col = 0, box = 6, completed = false),
        Cell(value = '7', row = 8, col = 1, box = 6, completed = true, userCell = false),
        Cell(value = '9', row = 8, col = 2, box = 6, completed = true, userCell = false),
        Cell(value = '5', row = 8, col = 3, box = 7, completed = true, userCell = false),
        Cell(value = '1', row = 8, col = 4, box = 7, completed = true, userCell = false),
        Cell(value = '3', row = 8, col = 5, box = 7, completed = true, userCell = false),
        Cell(value = '4', row = 8, col = 6, box = 8, completed = true, userCell = false),
        Cell(value = '8', row = 8, col = 7, box = 8, completed = true, userCell = false),
        Cell(value = '6', row = 8, col = 8, box = 8, completed = true, userCell = false),
    )

val mockedBoardSolved =
    listOf(
        Cell(value = '9', row = 0, col = 0, box = 0, completed = true, userCell = false),
        Cell(value = '5', row = 0, col = 1, box = 0, completed = true, userCell = false),
        Cell(value = '6', row = 0, col = 2, box = 0, completed = true, userCell = false),
        Cell(value = '4', row = 0, col = 3, box = 1, completed = true, userCell = false),
        Cell(value = '8', row = 0, col = 4, box = 1, completed = true, userCell = false),
        Cell(value = '7', row = 0, col = 5, box = 1, completed = true, userCell = false),
        Cell(value = '3', row = 0, col = 6, box = 2, completed = true, userCell = false),
        Cell(value = '2', row = 0, col = 7, box = 2, completed = true, userCell = false),
        Cell(value = '1', row = 0, col = 8, box = 2, completed = true, userCell = false),
        Cell(value = '7', row = 1, col = 0, box = 0, completed = true, userCell = false),
        Cell(value = '1', row = 1, col = 1, box = 0, completed = true, userCell = false),
        Cell(value = '8', row = 1, col = 2, box = 0, completed = true, userCell = false),
        Cell(value = '2', row = 1, col = 3, box = 1, completed = true, userCell = false),
        Cell(value = '3', row = 1, col = 4, box = 1, completed = true, userCell = false),
        Cell(value = '5', row = 1, col = 5, box = 1, completed = true, userCell = false),
        Cell(value = '6', row = 1, col = 6, box = 2, completed = true, userCell = false),
        Cell(value = '4', row = 1, col = 7, box = 2, completed = true, userCell = false),
        Cell(value = '9', row = 1, col = 8, box = 2, completed = true, userCell = false),
        Cell(value = '3', row = 2, col = 0, box = 0, completed = true, userCell = false),
        Cell(value = '2', row = 2, col = 1, box = 0, completed = true, userCell = false),
        Cell(value = '4', row = 2, col = 2, box = 0, completed = true, userCell = false),
        Cell(value = '9', row = 2, col = 3, box = 1, completed = true, userCell = false),
        Cell(value = '6', row = 2, col = 4, box = 1, completed = true, userCell = false),
        Cell(value = '1', row = 2, col = 5, box = 1, completed = true, userCell = false),
        Cell(value = '8', row = 2, col = 6, box = 2, completed = true, userCell = false),
        Cell(value = '7', row = 2, col = 7, box = 2, completed = true, userCell = false),
        Cell(value = '5', row = 2, col = 8, box = 2, completed = true, userCell = false),
        Cell(value = '4', row = 3, col = 0, box = 3, completed = true, userCell = false),
        Cell(value = '9', row = 3, col = 1, box = 3, completed = true, userCell = false),
        Cell(value = '2', row = 3, col = 2, box = 3, completed = true, userCell = false),
        Cell(value = '3', row = 3, col = 3, box = 4, completed = true, userCell = false),
        Cell(value = '5', row = 3, col = 4, box = 4, completed = true, userCell = false),
        Cell(value = '8', row = 3, col = 5, box = 4, completed = true, userCell = false),
        Cell(value = '1', row = 3, col = 6, box = 5, completed = true, userCell = false),
        Cell(value = '6', row = 3, col = 7, box = 5, completed = true, userCell = false),
        Cell(value = '7', row = 3, col = 8, box = 5, completed = true, userCell = false),
        Cell(value = '6', row = 4, col = 0, box = 3, completed = true, userCell = false),
        Cell(value = '8', row = 4, col = 1, box = 3, completed = true, userCell = false),
        Cell(value = '5', row = 4, col = 2, box = 3, completed = true, userCell = false),
        Cell(value = '1', row = 4, col = 3, box = 4, completed = true, userCell = false),
        Cell(value = '7', row = 4, col = 4, box = 4, completed = true, userCell = false),
        Cell(value = '9', row = 4, col = 5, box = 4, completed = true, userCell = false),
        Cell(value = '2', row = 4, col = 6, box = 5, completed = true, userCell = false),
        Cell(value = '3', row = 4, col = 7, box = 5, completed = true, userCell = false),
        Cell(value = '4', row = 4, col = 8, box = 5, completed = true, userCell = false),
        Cell(value = '1', row = 5, col = 0, box = 3, completed = true, userCell = false),
        Cell(value = '3', row = 5, col = 1, box = 3, completed = true, userCell = false),
        Cell(value = '7', row = 5, col = 2, box = 3, completed = true, userCell = false),
        Cell(value = '6', row = 5, col = 3, box = 4, completed = true, userCell = false),
        Cell(value = '4', row = 5, col = 4, box = 4, completed = true, userCell = false),
        Cell(value = '2', row = 5, col = 5, box = 4, completed = true, userCell = false),
        Cell(value = '9', row = 5, col = 6, box = 5, completed = true, userCell = false),
        Cell(value = '5', row = 5, col = 7, box = 5, completed = true, userCell = false),
        Cell(value = '8', row = 5, col = 8, box = 5, completed = true, userCell = false),
        Cell(value = '8', row = 6, col = 0, box = 6, completed = true, userCell = false),
        Cell(value = '6', row = 6, col = 1, box = 6, completed = true, userCell = false),
        Cell(value = '1', row = 6, col = 2, box = 6, completed = true, userCell = false),
        Cell(value = '7', row = 6, col = 3, box = 7, completed = true, userCell = false),
        Cell(value = '2', row = 6, col = 4, box = 7, completed = true, userCell = false),
        Cell(value = '4', row = 6, col = 5, box = 7, completed = true, userCell = false),
        Cell(value = '5', row = 6, col = 6, box = 8, completed = true, userCell = false),
        Cell(value = '9', row = 6, col = 7, box = 8, completed = true, userCell = false),
        Cell(value = '3', row = 6, col = 8, box = 8, completed = true, userCell = false),
        Cell(value = '5', row = 7, col = 0, box = 6, completed = true, userCell = false),
        Cell(value = '4', row = 7, col = 1, box = 6, completed = true, userCell = false),
        Cell(value = '3', row = 7, col = 2, box = 6, completed = true, userCell = false),
        Cell(value = '8', row = 7, col = 3, box = 7, completed = true, userCell = false),
        Cell(value = '9', row = 7, col = 4, box = 7, completed = true, userCell = false),
        Cell(value = '6', row = 7, col = 5, box = 7, completed = true, userCell = false),
        Cell(value = '7', row = 7, col = 6, box = 8, completed = true, userCell = false),
        Cell(value = '1', row = 7, col = 7, box = 8, completed = true, userCell = false),
        Cell(value = '2', row = 7, col = 8, box = 8, completed = true, userCell = false),
        Cell(value = '2', row = 8, col = 0, box = 6, completed = true, userCell = false),
        Cell(value = '7', row = 8, col = 1, box = 6, completed = true, userCell = false),
        Cell(value = '9', row = 8, col = 2, box = 6, completed = true, userCell = false),
        Cell(value = '5', row = 8, col = 3, box = 7, completed = true, userCell = false),
        Cell(value = '1', row = 8, col = 4, box = 7, completed = true, userCell = false),
        Cell(value = '3', row = 8, col = 5, box = 7, completed = true, userCell = false),
        Cell(value = '4', row = 8, col = 6, box = 8, completed = true, userCell = false),
        Cell(value = '8', row = 8, col = 7, box = 8, completed = true, userCell = false),
        Cell(value = '6', row = 8, col = 8, box = 8, completed = true, userCell = false),
    )

class JsonBoardSupplier
    @Inject
    constructor(
        @ApplicationContext private val context: Context,
    ) : BoardSupplier {
        override fun getBoard(size: Int): Flow<List<Cell>> =
            flow {
                emit(mockedBoard)
            }

        override fun getSolvedBoard(size: Int): Flow<List<Cell>> =
            flow {
                emit(mockedBoardSolved)
            }
    }
