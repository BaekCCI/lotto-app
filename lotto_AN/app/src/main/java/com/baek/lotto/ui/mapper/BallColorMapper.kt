package com.baek.lotto.ui.mapper

import androidx.compose.ui.graphics.Color
import com.baek.lotto.ui.theme.*

object BallColorMapper {
    fun getBallColor(n: Int): Color {
        return when (n) {
            in 1..10 -> LottoYellow
            in 11..20 -> LottoBlue
            in 21..30 -> LottoRed
            in 31..40 -> LottoGreen
            else -> LottoGray
        }
    }
}