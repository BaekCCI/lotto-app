package com.baek.lotto.data.remote.dto

data class DrawDto(
    val drwNo: Int,
    val drwDate: String,
    val numbers: List<Int>,
    val bonus: Int,
    val firstWinamnt: Long,
    val firstPrzwnerCo: Int
)