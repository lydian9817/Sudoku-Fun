package com.veragames.sudokufun.ui.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.veragames.sudokufun.domain.model.CellStatus
import com.veragames.sudokufun.ui.model.CellUI
import com.veragames.sudokufun.ui.theme.SudokuFunTheme
import com.veragames.sudokufun.ui.theme.green.userConflictCellText
import kotlin.math.sqrt

@Composable
fun Cell(
    cellUI: CellUI,
    onClick: (cell: CellUI) -> Unit,
    modifier: Modifier = Modifier,
) {
    val color: Color
    var textColor: Color

    when (cellUI.status) {
        CellStatus.NORMAL -> {
            color = MaterialTheme.colorScheme.secondaryContainer
            textColor = MaterialTheme.colorScheme.onSecondaryContainer
        }

        CellStatus.SELECTED -> {
            color = MaterialTheme.colorScheme.primaryContainer
            textColor = MaterialTheme.colorScheme.onPrimaryContainer
        }

        CellStatus.CONFLICT -> {
            color = MaterialTheme.colorScheme.error
            textColor = MaterialTheme.colorScheme.onError
        }

        CellStatus.COMMON_NUMBER -> {
            color = MaterialTheme.colorScheme.secondary
            textColor = MaterialTheme.colorScheme.onSecondary
        }

        CellStatus.IMPLICATED -> {
            color = MaterialTheme.colorScheme.tertiaryContainer
            textColor = MaterialTheme.colorScheme.onTertiaryContainer
        }
    }

    if (cellUI.cell.userCell && cellUI.cell.conflict) {
        textColor = userConflictCellText
    }
    if (cellUI.cell.userCell && cellUI.cell.completed) {
        textColor = MaterialTheme.colorScheme.primary
    }

    Box(
        modifier =
            modifier
                .background(color)
                .border(0.5.dp, Color.Black)
                .size(24.dp)
                .clickable { onClick(cellUI) },
        contentAlignment = Alignment.Center,
    ) {
        CommonText(
            text = cellUI.cell.value.toString(),
            color = textColor,
        )
    }
}

@Composable
fun Board(
    cellList: List<CellUI>,
    onCellClick: (cell: CellUI) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (cellList.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(sqrt(cellList.size.toDouble()).toInt()),
            userScrollEnabled = false,
            modifier = modifier.wrapContentSize(),
        ) {
            items(cellList) { cell ->
                Cell(cellUI = cell, onClick = onCellClick)
            }
        }
    }
}

@Preview
@Composable
private fun BoardPrev() {
    SudokuFunTheme {
        // Board(mockedBoard, {})
    }
}
