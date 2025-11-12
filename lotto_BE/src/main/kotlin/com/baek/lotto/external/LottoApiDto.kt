package com.baek.lotto.external

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class LottoApiDto(
    val totSellamnt: Long? = null,
    val returnValue: String? = null,
    val drwNoDate: String? = null,
    val firstWinamnt: Long? = null,
    val drwtNo6: Int? = null,
    val drwtNo4: Int? = null,
    val firstPrzwnerCo: Int? = null,
    val drwtNo5: Int? = null,
    val bnusNo: Int? = null,
    val firstAccumamnt: Long? = null,
    val drwNo: Int? = null,
    val drwtNo2: Int? = null,
    val drwtNo3: Int? = null,
    val drwtNo1: Int? = null
)

