package com.veragames.sudokufun.ui.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.veragames.sudokufun.R
import com.veragames.sudokufun.ui.theme.SudokuFunTheme

@Composable
fun BoardInfo(
    difficulty: String,
    currentTime: String,
    mistakes: Int,
    maxMistakes: Int,
    modifier: Modifier = Modifier,
) {
    val textColor = MaterialTheme.colorScheme.onSurface
    Row(
        modifier =
            modifier
                .padding(vertical = 24.dp)
                .wrapContentHeight()
                .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CommonText(
            text = difficulty,
            color = textColor,
            modifier = Modifier.weight(1f),
        )
        CommonText(
            text = currentTime,
            color = textColor,
            modifier = Modifier.weight(1f),
        )
        MistakesInfo(
            mistakes = mistakes,
            maxMistakes = maxMistakes,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
fun MistakesInfo(
    mistakes: Int,
    maxMistakes: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = Icons.Filled.Warning,
            contentDescription = null,
        )
        Spacer(Modifier.width(4.dp))
        CommonText(
            text = stringResource(R.string.mistakes, mistakes, maxMistakes),
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showSystemUi = false,
    showBackground = true,
)
@Composable
private fun BoardInfoPrev() {
    SudokuFunTheme {
        BoardInfo("Easy", "00:00", 0, 3)
    }
}
