package com.baek.lotto.external

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class LottoApiDto(
    val drwNo: Int?,
    val drwNoDate: String?,
    val drwtNo1: Int?,
    val drwtNo2: Int?,
    val drwtNo3: Int?,
    val drwtNo4: Int?,
    val drwtNo5: Int?,
    val drwtNo6: Int?,
    val bnusNo: Int?,
    val firstWinamnt: Long?,
    val firstPrzwnerCo: Int?,
    val returnValue: String
)
