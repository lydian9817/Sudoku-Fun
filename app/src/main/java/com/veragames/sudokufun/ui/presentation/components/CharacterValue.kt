package com.veragames.sudokufun.ui.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.veragames.sudokufun.data.model.SudokuValue
import com.veragames.sudokufun.ui.Dimens
import com.veragames.sudokufun.ui.util.TestTags

@Composable
fun CharacterValue(
    value: SudokuValue,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(Dimens.GAME_BUTTON_CORNER_RADIUS),
        colors = CardDefaults.cardColors().copy(containerColor = Color.Transparent),
        modifier =
            modifier
                .fillMaxSize()
                .testTag(TestTags.getSudokuValueTestTag(value)),
    ) {
        CommonText(
            text = value.value.toString(),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally),
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
