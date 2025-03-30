package com.veragames.sudokufun.ui.presentation.gamescreen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.veragames.sudokufun.domain.model.BoardSize
import com.veragames.sudokufun.ui.presentation.components.Board
import com.veragames.sudokufun.ui.presentation.components.BoardInfo
import com.veragames.sudokufun.ui.presentation.components.CharacterValue
import com.veragames.sudokufun.ui.presentation.components.GameButtonRow
import com.veragames.sudokufun.ui.presentation.components.GameDialog
import com.veragames.sudokufun.ui.presentation.components.GameTopBar

@Composable
fun GameScreen(
    onBackToMainScreenClick: () -> Unit,
    viewModel: GameViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            GameTopBar(
                userScore = state.score,
                onBackClick = {},
                onThemeClick = {},
                onSettingsClick = {},
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 12.dp, vertical = 12.dp)
                    .consumeWindowInsets(paddingValues)
                    .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            BoardInfo(
                difficulty = state.difficulty,
                currentTime = state.time,
                mistakes = state.mistakes,
                maxMistakes = state.maxMistakes,
            )
            Board(
                cellList = state.board,
                onCellClick = { viewModel.selectCell(it) },
                isGameRunning = state.gameRunning || state.completed,
            )

            GameButtonRow(
                onUndo = viewModel::undoMovement,
                onHint = viewModel::showHint,
                onNotes = {
                    if (state.notesEnabled) {
                        viewModel.enableNotes(false)
                    } else {
                        viewModel.enableNotes(true)
                    }
                },
                onErase = viewModel::eraseCellValue,
                onPause = viewModel::pauseGame,
                hintEnabled = state.hintEnabled,
                notesEnabled = state.notesEnabled,
            )
            LazyVerticalGrid(
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalArrangement = Arrangement.Center,
                userScrollEnabled = false,
                columns = GridCells.Fixed(BoardSize.NINE.size),
            ) {
                items(BoardSize.NINE.values) { value ->
                    CharacterValue(
                        value = value,
                        onClick = {
                            if (state.notesEnabled) {
                                viewModel.noteValue(value)
                            } else {
                                viewModel.setCellValue(value)
                            }
                        },
                    )
                }
            }
        }
    }

    if (state.completed) {
        GameDialog(
            time = state.time,
            mistakes = state.mistakes,
            maxMistakes = state.maxMistakes,
            difficulty = state.difficulty,
            onButtonClick = onBackToMainScreenClick,
            gameCompleted = true,
        )
    } else if (state.gameRunning.not()) {
        GameDialog(
            time = state.time,
            mistakes = state.mistakes,
            maxMistakes = state.maxMistakes,
            difficulty = state.difficulty,
            onButtonClick = viewModel::resumeGame,
        )
    }

    BackHandler {
        if (state.gameRunning) {
            viewModel.pauseGame()
        }
    }

    LifecycleEventEffect(Lifecycle.Event.ON_PAUSE) {
        viewModel.pauseGame()
    }

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        viewModel.resumeGame()
    }
}
