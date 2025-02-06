package com.veragames.sudokufun.ui.presentation.components

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.veragames.sudokufun.ui.theme.SudokuFunTheme
import com.veragames.sudokufun.ui.theme.green.primaryLight

@Composable
fun MainScreenButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier.wrapContentSize(Alignment.Center),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = primaryLight), // TODO ver cambios de tema
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 8.dp
        )
    ) {
        CommonText(
            text = text,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview
@Composable
private fun MainScreenButtonPrev() {
    SudokuFunTheme {
        MainScreenButton(
            text = "Nuevo Juego",
            onClick = {},
        )
    }
}
