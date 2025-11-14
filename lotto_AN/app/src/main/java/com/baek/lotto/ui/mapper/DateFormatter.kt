package com.baek.lotto.ui.mapper

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateFormatter {
    private val displayFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd")

    fun LocalDate.toUiString():String{
        return this.format(displayFormat)
    }
}