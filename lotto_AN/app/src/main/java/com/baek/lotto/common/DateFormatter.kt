package com.baek.lotto.common

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateFormatter {
    private val displayFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd")

    fun LocalDate.toYmdString(): String {
        return this.format(displayFormat)
    }

    fun String.toLocalDate(): LocalDate {
        return LocalDate.parse(this, displayFormat)
    }
}