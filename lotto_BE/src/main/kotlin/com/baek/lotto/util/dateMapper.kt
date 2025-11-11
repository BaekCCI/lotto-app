package com.baek.lotto.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter


private val DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd")

fun String.toLocalDate(): LocalDate {
    return LocalDate.parse(this, DATE_FMT)
}
