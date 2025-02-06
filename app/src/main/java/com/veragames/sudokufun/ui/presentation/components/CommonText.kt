package com.veragames.sudokufun.ui.presentation.components

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.veragames.sudokufun.ui.theme.SudokuFunTheme

@Composable
fun CommonText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    style: TextStyle = LocalTextStyle.current,
    maxLines: Int = 1,
) {
    Text(
        text = text,
        color = color,
        modifier = modifier,
        maxLines = maxLines,
        textAlign = TextAlign.Center,
        style = style,
    )
}

@Preview
@Composable
private fun CommonTextPrev() {
    SudokuFunTheme {
        CommonText(
            text = "Hola",
            color = Color.Black,
        )
    }
}
