package com.veragames.sudokufun.ui.presentation.gamescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.veragames.sudokufun.ui.presentation.components.Board

@Composable
fun GameScreen(viewModel: GameViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    Column {
        Board(
            cellList = state.board,
            onCellClick = { viewModel.onCellClicked(it) },
        )
    }
}
