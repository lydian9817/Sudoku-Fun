package com.veragames.sudokufun.ui.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.veragames.sudokufun.ui.theme.green.greenDarkScheme
import com.veragames.sudokufun.ui.theme.green.greenLightScheme

@Composable
fun SudokuFunTheme(
    darkTheme: Boolean = false, // TODO isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme =
        when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                val context = LocalContext.current
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            }

            darkTheme -> greenDarkScheme // TODO: ver cambios de tema
            else -> greenLightScheme
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typo,
        content = content,
    )
}
