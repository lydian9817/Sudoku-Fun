package com.veragames.sudokufun.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.veragames.sudokufun.R

// Set of Material typography styles to start with
val Typography =
    Typography(
        bodyLarge =
            TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp,
            ),
        /* Other default text styles to override
        titleLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        labelSmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        )
         */
    )

val shantellSansFamily =
    FontFamily(
        Font(R.font.shantell_sans_light, FontWeight.Light),
        Font(R.font.shantell_sans, FontWeight.Normal),
        Font(R.font.shantell_sans_bold, FontWeight.Bold),
        Font(R.font.shantell_sans_medium, FontWeight.Medium),
        Font(R.font.shantell_sans_semibold, FontWeight.SemiBold),
    )

val Typo =
    Typography.copy(
        displayLarge = Typography.displayLarge.copy(fontFamily = shantellSansFamily),
        displayMedium = Typography.displayMedium.copy(fontFamily = shantellSansFamily),
        displaySmall = Typography.displaySmall.copy(fontFamily = shantellSansFamily),
        headlineLarge = Typography.headlineLarge.copy(fontFamily = shantellSansFamily),
        headlineMedium = Typography.headlineMedium.copy(fontFamily = shantellSansFamily),
        headlineSmall = Typography.headlineSmall.copy(fontFamily = shantellSansFamily),
        titleLarge = Typography.titleLarge.copy(fontFamily = shantellSansFamily),
        titleMedium = Typography.titleMedium.copy(fontFamily = shantellSansFamily),
        titleSmall = Typography.titleSmall.copy(fontFamily = shantellSansFamily),
        bodyLarge = Typography.bodyLarge.copy(fontFamily = shantellSansFamily),
        bodyMedium = Typography.bodyMedium.copy(fontFamily = shantellSansFamily),
        bodySmall = Typography.bodySmall.copy(fontFamily = shantellSansFamily),
        labelLarge = Typography.labelLarge.copy(fontFamily = shantellSansFamily),
        labelMedium = Typography.labelMedium.copy(fontFamily = shantellSansFamily),
        labelSmall = Typography.labelSmall.copy(fontFamily = shantellSansFamily),
    )
