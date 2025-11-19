package com.baek.lotto.ui.mapper

import com.baek.lotto.domain.model.Draw
import com.baek.lotto.domain.model.DrawInfo
import com.baek.lotto.domain.model.RandomLotto
import com.baek.lotto.ui.mapper.BallColorMapper.getBallColor
import com.baek.lotto.common.DateFormatter.toYmdString
import com.baek.lotto.domain.model.LottoRecord
import com.baek.lotto.ui.model.DrawInfoUiModel
import com.baek.lotto.ui.model.DrawUiModel
import com.baek.lotto.ui.model.NumberUiModel
import com.baek.lotto.ui.model.RandomLottoUiModel
import com.baek.lotto.ui.model.StorageGroupUiModel
import com.baek.lotto.ui.model.StorageItemUiModel
import java.time.LocalDate

object UiMapper {

    fun Draw.toUi(): DrawUiModel {
        return DrawUiModel(
            drawNo = drawNo,
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

    fun RandomLotto.toUi(): RandomLottoUiModel {
        return RandomLottoUiModel(
            numbers = numbers.map { it.toUi() }
        )
    }

    fun List<LottoRecord>.toUiGroup(): List<StorageGroupUiModel> {
        val grouped = this.groupBy { it.createdAt }

        return grouped.map { (date, records) ->
            StorageGroupUiModel(
                id = date.hashCode().toLong(),
                date = date.toYmdString(),
                isExpanded = true,
                items = records.map { it.toUi() }
            )
        }
    }

    fun LottoRecord.toUi(): StorageItemUiModel {
        return StorageItemUiModel(
            id = id,
            numbers = numbers.map { it.toUi() }
        )
    }

    private fun buildTitle(drawNo: Int, date: LocalDate): String {
        val dateText = date.toYmdString()
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