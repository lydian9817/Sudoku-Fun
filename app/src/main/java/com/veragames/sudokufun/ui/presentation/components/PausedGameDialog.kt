package com.veragames.sudokufun.ui.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.veragames.sudokufun.R
import com.veragames.sudokufun.ui.Dimens
import com.veragames.sudokufun.ui.theme.SudokuFunTheme
import com.veragames.sudokufun.ui.util.TestTags

@Composable
fun PausedGameDialog(
    time: String,
    mistakes: Int,
    maxMistakes: Int,
    difficulty: String,
    onResumeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Dialog(
        onDismissRequest = {},
        properties =
            DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false,
            ),
    ) {
        Card(
            modifier = modifier.testTag(TestTags.PAUSED_DIALOG),
            shape = RoundedCornerShape(Dimens.GENERAL_CORNER_RADIUS),
            colors =
                CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
            elevation = CardDefaults.cardElevation(defaultElevation = Dimens.GENERAL_ELEVATION),
        ) {
            Column(
                modifier = Modifier.padding(24.dp).align(Alignment.CenterHorizontally),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                CommonText(
                    text = stringResource(R.string.game_paused),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(vertical = 8.dp),
                    style = MaterialTheme.typography.headlineMedium,
                )
                Row(
                    modifier =
                        Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    DialogGameInfoItem(
                        titleId = R.string.time,
                        value = time,
                        modifier = Modifier.weight(1f),
                    )
                    DialogGameInfoItem(
                        titleId = R.string.mistakes_txt,
                        value = stringResource(R.string.mistakes, mistakes, maxMistakes),
                        modifier = Modifier.weight(1f),
                    )
                    DialogGameInfoItem(
                        titleId = R.string.difficulty,
                        value = difficulty,
                        modifier = Modifier.weight(1f),
                    )
                }
                GeneralButton(
                    text = stringResource(R.string.resume),
                    onClick = onResumeClick,
                    modifier = Modifier.padding(horizontal = 16.dp).testTag(TestTags.RESUME_BUTTON),
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                        ),
                    textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    borderStroke = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                )
            }
        }
    }
}

@Composable
fun DialogGameInfoItem(
    @StringRes titleId: Int,
    value: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CommonText(
            text = stringResource(titleId),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelSmall,
        )
        CommonText(
            text = value,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Preview
@Composable
private fun PausedGameDialogPrev() {
    SudokuFunTheme {
        PausedGameDialog(
            time = "01:00",
            mistakes = 1,
            maxMistakes = 3,
            difficulty = "Normal",
            onResumeClick = {},
        )
    }
}
