package com.baek.lotto.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val LightColorScheme = lightColorScheme(
    primary = LottoYellow,
    secondary = LottoBlue,
    tertiary = LottoRed,

    background = Background,
    surface = Surface
)

@Composable
fun LottoTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = LottoTypography,
        content = content
    )
}