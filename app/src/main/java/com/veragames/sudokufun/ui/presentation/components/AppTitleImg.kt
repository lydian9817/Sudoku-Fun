package com.veragames.sudokufun.ui.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.veragames.sudokufun.R
import com.veragames.sudokufun.ui.theme.SudokuFunTheme

@Composable
fun AppTitleImg(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.sudoku_fun_title_img),
        contentDescription = "Logo",
        modifier = modifier,

    )
}

@Preview
@Composable
private fun AppTitleImgPrev() {
    SudokuFunTheme {
        AppTitleImg()
    }
}
