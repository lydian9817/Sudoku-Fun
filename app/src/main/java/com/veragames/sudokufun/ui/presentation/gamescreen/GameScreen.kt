package com.veragames.sudokufun.ui.presentation.gamescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.veragames.sudokufun.data.model.SudokuValue
import com.veragames.sudokufun.ui.presentation.components.Board
import com.veragames.sudokufun.ui.presentation.components.CharacterValue

@Composable
fun GameScreen(viewModel: GameViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    Column(
        modifier =
            Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 12.dp)
                .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Board(
            cellList = state.board,
            onCellClick = { viewModel.onCellClicked(it) },
        )
        Spacer(modifier = Modifier.height(48.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            userScrollEnabled = false,
        ) {
            items(SudokuValue.NINE_VALUES) { value ->
                CharacterValue(
                    value = value,
                    onClick = { viewModel.setCellValue(value) },
                )
            }
        }
    }
}
