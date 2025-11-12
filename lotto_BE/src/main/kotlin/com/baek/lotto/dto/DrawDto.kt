package com.baek.lotto.dto

import java.time.LocalDate

data class DrawDto(
    val drwNo: Int,
    val drwDate: LocalDate,
    val numbers: List<Int>,
    val bonus: Int,
    val firstWinamnt: Long,
    val firstPrzwnerCo: Int
)
