package com.baek.lotto.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import kotlinx.coroutines.delay

@Composable
fun TypingText(
    text: String,
    interval: Long = 100L,
    color: Color = Color.White,
    style: TextStyle,
    isRepeat: Boolean = true
) {
    var visibleText by remember { mutableStateOf("") }

    LaunchedEffect(text) {
        do {
            visibleText = ""
            text.forEachIndexed { index, _ ->
                visibleText = text.take(index + 1)
                delay(interval)
            }

            if (isRepeat) {
                delay(1500)
            }
        } while (isRepeat)
    }
    Text(
        text = visibleText,
        color = color,
        style = style
    )
}