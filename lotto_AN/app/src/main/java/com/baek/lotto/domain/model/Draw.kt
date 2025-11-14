package com.baek.lotto.domain.model

import java.time.LocalDate

data class Draw(
    val drawNo: Int,
    val drawDate: LocalDate,
    val numbers: List<Int>,
    val bonus: Int,
    val firstPrize: Long,
    val firstWinnerCount: Int
)