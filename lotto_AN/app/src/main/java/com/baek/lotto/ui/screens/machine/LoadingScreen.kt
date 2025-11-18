package com.baek.lotto.ui.screens.machine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baek.lotto.ui.theme.LottoTypography
import com.dotlottie.dlplayer.Mode
import com.lottiefiles.dotlottie.core.compose.ui.DotLottieAnimation
import com.lottiefiles.dotlottie.core.util.DotLottieSource
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DotLottieAnimation(
            source = DotLottieSource.Url("https://lottie.host/099140e8-ca20-4f39-817b-edbc3a8fe6a3/9vMvhmCN4W.lottie"),
            autoplay = true,
            loop = true,
            speed = 3f,
            useFrameInterpolation = false,
            playMode = Mode.FORWARD,
            modifier = Modifier.background(Transparent)
        )
        Spacer(Modifier.height(24.dp))

        TypingText(
            text = "로또 뽑는 중...",
            style = LottoTypography.headlineSmall,
            color = Color.White,
            interval = 100L
        )
    }
}

@Composable
fun TypingText(
    text: String,
    interval: Long = 100L,
    color: Color = Color.White,
    style: TextStyle
) {
    var visibleText by remember { mutableStateOf("") }

    LaunchedEffect(text) {
        visibleText = ""
        text.forEachIndexed { index, _ ->
            visibleText = text.take(index + 1)
            delay(interval)
        }
        while (true) {
            visibleText = ""
            text.forEachIndexed { index, _ ->
                visibleText = text.take(index + 1)
                delay(interval)
            }
            delay(1500)
        }
    }
    Text(
        text = visibleText,
        color = color,
        style = style
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, backgroundColor = 0xFFE5E5E5)
@Composable
fun LottoMachineLoadingScreenPreview() {
    LoadingScreen()
}

