package com.baek.lotto.ui.mapper

import com.baek.lotto.domain.model.Draw
import com.baek.lotto.domain.model.DrawInfo
import com.baek.lotto.ui.mapper.BallColorMapper.getBallColor
import com.baek.lotto.ui.mapper.DateFormatter.toUiString
import com.baek.lotto.ui.model.DrawInfoUiModel
import com.baek.lotto.ui.model.DrawUiModel
import com.baek.lotto.ui.model.NumberUiModel
import java.time.LocalDate

object UiMapper {

    fun Draw.toUi(): DrawUiModel {
        return DrawUiModel(
            title = buildTitle(drawNo, drawDate),
            numbers = numbers.map { it.toUi() },
            bonus = bonus.toUi(),
            firstPrizeFormatted = firstPrize.format(),
            firstWinnerCount = firstWinnerCount
        )
    }

    fun DrawInfo.toUi(): DrawInfoUiModel {
        return DrawInfoUiModel(
            drawNo = drawNo,
            title = buildTitle(drawNo, date)
        )
    }

    fun List<Int>.toUi(): List<NumberUiModel> {
        return this.map { n ->
            n.toUi()
        }
    }

    private fun buildTitle(drawNo: Int, date: LocalDate): String {
        val dateText = date.toUiString()
        return "${drawNo}회차(${dateText})"
    }

    private fun Int.toUi(): NumberUiModel {
        return NumberUiModel(
            number = this,
            color = getBallColor(this)
        )
    }

    private fun Long.format(): String {
        return "%,d".format(this)
    }
}