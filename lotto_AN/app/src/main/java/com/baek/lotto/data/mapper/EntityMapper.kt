package com.baek.lotto.data.mapper

import com.baek.lotto.common.DateFormatter.toLocalDate
import com.baek.lotto.data.local.entity.LottoRecordEntity
import com.baek.lotto.domain.model.LottoRecord
import com.baek.lotto.common.DateFormatter.toYmdString
import com.baek.lotto.domain.model.RandomLotto
import java.time.LocalDate

object EntityMapper {

    fun RandomLotto.toEntity(now: LocalDate): LottoRecordEntity {
        return LottoRecordEntity(
            numbers = numbers.joinToString(","),
            createdAt = now.toYmdString()
        )
    }

    fun LottoRecordEntity.toDomain(): LottoRecord {
        return LottoRecord(
            id = id,
            numbers = numbers.split(",").map { it.toInt() },
            createdAt = createdAt.toLocalDate()
        )
    }
}