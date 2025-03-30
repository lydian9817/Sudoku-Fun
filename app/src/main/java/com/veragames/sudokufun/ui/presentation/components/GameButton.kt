package com.veragames.sudokufun.ui.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
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
import com.veragames.sudokufun.ui.util.TestTags

@Composable
fun GameButtonContent(
    @StringRes textId: Int,
    @DrawableRes iconId: Int,
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Icon(
            painter = painterResource(iconId),
            contentDescription = stringResource(textId),
            tint = contentColor,
            modifier =
                Modifier
                    .size(24.dp),
        )
        CommonText(
            text = stringResource(textId),
            color = contentColor,
        )
    }
}

@Composable
fun GameButton(
    @StringRes textId: Int,
    @DrawableRes iconId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerColor: Color = Color.Transparent,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    hasBadge: Boolean = false,
    badgeContent: String? = null,
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(Dimens.GAME_BUTTON_CORNER_RADIUS),
        modifier = modifier,
        colors =
            CardDefaults
                .cardColors()
                .copy(
                    containerColor = containerColor,
                    disabledContainerColor = containerColor,
                ),
        enabled = enabled,
    ) {
        if (hasBadge) {
            BadgedBox(
                badge = {
                    Badge(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    ) {
                        badgeContent?.let {
                            CommonText(
                                text = it,
                                color = MaterialTheme.colorScheme.onTertiaryContainer,
                            )
                        }
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ) {
                GameButtonContent(
                    textId = textId,
                    iconId = iconId,
                    contentColor = contentColor,
                )
            }
        } else {
            GameButtonContent(
                textId = textId,
                iconId = iconId,
                contentColor = contentColor,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
        }
    }
}

@Composable
fun GameButtonRow(
    onUndo: () -> Unit,
    onErase: () -> Unit,
    onNotes: () -> Unit,
    onHint: () -> Unit,
    onPause: () -> Unit,
    hintEnabled: Boolean,
    notesEnabled: Boolean,
    modifier: Modifier = Modifier,
    hintsRemaining: Int = 0,
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
                    .testTag(TestTags.UNDO_BUTTON),
        )
        GameButton(
            textId = R.string.erase,
            iconId = R.drawable.icon_erase,
            onClick = onErase,
            modifier =
                Modifier
                    .weight(1f)
                    .testTag(TestTags.ERASE_BUTTON),
        )
        GameButton(
            textId = R.string.notes,
            iconId = R.drawable.icon_notes,
            onClick = onNotes,
            contentColor =
                if (notesEnabled) {
                    MaterialTheme.colorScheme.onTertiaryContainer
                } else {
                    MaterialTheme.colorScheme.onSurface
                },
            containerColor =
                if (notesEnabled) {
                    MaterialTheme.colorScheme.tertiaryContainer
                } else {
                    Color.Transparent
                },
            modifier =
                Modifier
                    .weight(1f)
                    .testTag(TestTags.NOTES_BUTTON),
        )
        GameButton(
            textId = R.string.hint,
            iconId = R.drawable.icon_hint,
            onClick = onHint,
            enabled = hintEnabled,
            hasBadge = true,
            badgeContent = hintsRemaining.toString(),
            contentColor =
                if (hintEnabled) {
                    MaterialTheme.colorScheme.onSurface
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                },
            modifier =
                Modifier
                    .weight(1f)
                    .testTag(TestTags.HINT_BUTTON),
        )
        GameButton(
            textId = R.string.pause,
            iconId = R.drawable.icon_pause,
            onClick = onPause,
            modifier =
                Modifier
                    .weight(1f)
                    .testTag(TestTags.PAUSE_BUTTON),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ButtonRowPrev() {
    SudokuFunTheme {
        GameButtonRow(
            onUndo = {},
            onNotes = {},
            onHint = {},
            onErase = {},
            onPause = {},
            hintEnabled = false,
            notesEnabled = true,
            hintsRemaining = 3,
        )
    }
}
