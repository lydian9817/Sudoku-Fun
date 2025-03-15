package com.veragames.sudokufun.ui.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.veragames.sudokufun.R
import com.veragames.sudokufun.ui.Dimens
import com.veragames.sudokufun.ui.theme.SudokuFunTheme

@Composable
fun GameButton(
    @StringRes textId: Int,
    @DrawableRes iconId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(Dimens.GAME_BUTTON_CORNER_RADIUS),
        modifier = modifier,
        colors = CardDefaults.cardColors().copy(containerColor = Color.Transparent),
    ) {
        Icon(
            painter = painterResource(iconId),
            contentDescription = stringResource(textId),
            tint = MaterialTheme.colorScheme.onSurface,
            modifier =
                Modifier
                    .size(24.dp)
                    .align(Alignment.CenterHorizontally),
        )
        CommonText(
            text = stringResource(textId),
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
    }
}

@Composable
fun GameButtonRow(
    onUndo: () -> Unit,
    onErase: () -> Unit,
    onNotes: () -> Unit,
    onHint: () -> Unit,
    onPause: () -> Unit,
    onResumeGame: () -> Unit,
    gameRunning: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GameButton(
            textId = R.string.undo,
            iconId = R.drawable.icon_undo,
            onClick = onUndo,
            modifier =
                Modifier
                    .weight(1f)
                    .testTag("undo_button"),
        )
        GameButton(
            textId = R.string.erase,
            iconId = R.drawable.icon_erase,
            onClick = onErase,
            modifier =
                Modifier
                    .weight(1f)
                    .testTag("erase_button"),
        )
        GameButton(
            textId = R.string.notes,
            iconId = R.drawable.icon_notes,
            onClick = onNotes,
            modifier =
                Modifier
                    .weight(1f)
                    .testTag("notes_button"),
        )
        GameButton(
            textId = R.string.hint,
            iconId = R.drawable.icon_hint,
            onClick = onHint,
            modifier =
                Modifier
                    .weight(1f)
                    .testTag("hint_button"),
        )
        if (gameRunning) {
            GameButton(
                textId = R.string.pause,
                iconId = R.drawable.icon_pause,
                onClick = onPause,
                modifier =
                    Modifier
                        .weight(1f)
                        .testTag("pause_button"),
            )
        } else {
            GameButton(
                textId = R.string.resume,
                iconId = R.drawable.icon_play,
                onClick = onResumeGame,
                modifier =
                    Modifier
                        .weight(1f)
                        .testTag("resume_button"),
            )
        }
    }
}

@Preview
@Composable
private fun ButtonRowPrev() {
    SudokuFunTheme {
        GameButtonRow(
            onUndo = {},
            onNotes = {},
            onHint = {},
            onErase = {},
            onPause = {},
            onResumeGame = {},
            gameRunning = true,
        )
    }
}
