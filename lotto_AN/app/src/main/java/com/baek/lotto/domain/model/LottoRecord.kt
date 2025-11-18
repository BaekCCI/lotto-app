package com.baek.lotto.domain.model

import java.time.LocalDate

data class LottoRecord(
    val id: Long,
    val numbers: List<Int>,
    val createdAt: LocalDate
)