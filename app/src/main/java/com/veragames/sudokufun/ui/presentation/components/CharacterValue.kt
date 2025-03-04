package com.veragames.sudokufun.ui.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.veragames.sudokufun.data.model.SudokuValue

@Composable
fun CharacterValue(
    value: SudokuValue,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .height(72.dp)
                .width(24.dp)
                .clickable { onClick() }
                .background(Color.Transparent),
        contentAlignment = Alignment.Center,
    ) {
        CommonText(
            text = value.value.toString(),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showSystemUi = true,
    showBackground = true,
)
@Composable
private fun Preview_CharacterValue() {
    CharacterValue(SudokuValue.A, {})
}
