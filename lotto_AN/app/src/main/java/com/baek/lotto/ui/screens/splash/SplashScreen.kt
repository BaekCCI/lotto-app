package com.baek.lotto.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.baek.lotto.ui.theme.*
import androidx.compose.ui.graphics.Color

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Surface)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "인생은\n한방이다.",
                color = Black,
                style = LottoTypography.displayLarge,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Life is beautiful",
                color = TextPrimary,
                style = LottoTypography.bodyLarge
            )
            Spacer(Modifier.height(80.dp))

        }
        LottoLogo(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp)
        )
    }
}

@Composable
private fun LottoLogo(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LottoCircle("L", LottoYellow)
        LottoCircle("O", LottoBlue)
        LottoCircle("T", LottoRed)
        LottoCircle("T", LottoGray)
        LottoCircle("O", LottoGreen)
    }
}

@Composable
private fun LottoCircle(
    text: String,
    background: Color,
) {
    Box(
        modifier = Modifier
            .size(45.dp)
            .background(background, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Surface,
            style = LottoTypography.displaySmall
        )
    }
}

@Preview
@Composable
fun SplashPreview() {
    SplashScreen()
}