package com.veragames.sudokufun.ui.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.veragames.sudokufun.data.mockedBoard
import com.veragames.sudokufun.domain.model.CellStatus
import com.veragames.sudokufun.ui.drawAllBorders
import com.veragames.sudokufun.ui.drawBottomBorder
import com.veragames.sudokufun.ui.drawRightBorder
import com.veragames.sudokufun.ui.model.CellUI
import com.veragames.sudokufun.ui.theme.SudokuFunTheme
import com.veragames.sudokufun.ui.theme.green.userConflictCellText
import kotlin.math.sqrt

private const val CELL_BORDER_WIDTH_NORMAL = 3f
private const val CELL_BORDER_WIDTH_THICK = CELL_BORDER_WIDTH_NORMAL.times(2.5f)

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
                // .border(0.5.dp, Color.Black)
                .clickable { onClick(cellUI) },
        contentAlignment = Alignment.Center,
    ) {
        CommonText(
            text = cellUI.cell.value.toString(),
            color = textColor,
            style = MaterialTheme.typography.titleLarge,
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
        val boardSize = sqrt(cellList.size.toDouble()).toInt()
        val boxSize = sqrt(boardSize.toDouble()).toInt()
        val borderColor = MaterialTheme.colorScheme.outline
        LazyVerticalGrid(
            columns = GridCells.Fixed(boardSize),
            userScrollEnabled = false,
            modifier =
                modifier.wrapContentSize().border(2.dp, borderColor),
        ) {
            items(cellList) { cellUI ->
                Cell(
                    cellUI = cellUI,
                    onClick = onCellClick,
                    modifier =
                        Modifier.aspectRatio(1f).drawWithContent {
                            drawContent()
                            drawAllBorders(borderColor, CELL_BORDER_WIDTH_NORMAL)

                            // Engrosado de bordes
                            if ((cellUI.cell.col + 1) % boxSize == 0 && cellUI.cell.col < boardSize - 1) {
                                drawRightBorder(borderColor, CELL_BORDER_WIDTH_THICK)
                            }
                            if ((cellUI.cell.row + 1) % boxSize == 0) {
                                drawBottomBorder(borderColor, CELL_BORDER_WIDTH_THICK)
                            }
                        },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BoardPrev() {
    SudokuFunTheme {
        Board(mockedBoard.map { CellUI(it) }, {})
    }
}
