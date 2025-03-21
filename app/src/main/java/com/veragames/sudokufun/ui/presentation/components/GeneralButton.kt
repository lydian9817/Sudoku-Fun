package com.veragames.sudokufun.ui.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.veragames.sudokufun.ui.Dimens
import com.veragames.sudokufun.ui.theme.SudokuFunTheme

@Composable
fun GeneralButton(
    text: String,
    textColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    borderStroke: BorderStroke? = null,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = colors,
        shape = RoundedCornerShape(Dimens.GENERAL_CORNER_RADIUS),
        elevation =
            ButtonDefaults.buttonElevation(
                defaultElevation = Dimens.GENERAL_ELEVATION,
                pressedElevation = Dimens.GENERAL_PRESSED_ELEVATION,
            ),
        border = borderStroke,
    ) {
        CommonText(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = textColor,
            modifier = Modifier.padding(horizontal = 8.dp),
        )
    }
}

@Preview
@Composable
private fun GeneralButtonPrev() {
    SudokuFunTheme {
        GeneralButton(
            text = "Nuevo Juego",
            onClick = {},
            textColor = MaterialTheme.colorScheme.onPrimary,
        )
    }
}
