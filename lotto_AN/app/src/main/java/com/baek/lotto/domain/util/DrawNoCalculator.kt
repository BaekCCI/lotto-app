package com.baek.lotto.domain.util

import com.baek.lotto.domain.constant.LottoConstants.FIRST_DRAW_DATE
import com.baek.lotto.domain.model.DrawInfo
import java.time.LocalDate

object DrawNoCalculator {

    fun buildDrawInfoList(latestDrawNo: Int): List<DrawInfo> {
        return (latestDrawNo downTo 1).map { drawNo ->
            DrawInfo(
                drawNo = drawNo,
                date = calculateDate(drawNo)
            )
        }
    }

    private fun calculateDate(drawNo: Int): LocalDate {
        return FIRST_DRAW_DATE.plusWeeks((drawNo - 1).toLong())
    }
}