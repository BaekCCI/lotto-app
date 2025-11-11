package com.baek.lotto.external

import com.baek.lotto.domain.entity.DrawEntity
import com.baek.lotto.util.toLocalDate

object LottoApiMapper {
    fun LottoApiDto.toEntity(): DrawEntity {
        return DrawEntity(
            drwNo = drwNo!!,
            drwDate = drwNoDate!!.toLocalDate(),
            n1 = drwtNo1!!,
            n2 = drwtNo2!!,
            n3 = drwtNo3!!,
            n4 = drwtNo4!!,
            n5 = drwtNo5!!,
            n6 = drwtNo6!!,
            bonus = bnusNo!!,
            firstWinamnt = firstWinamnt!!,
            firstPrzwnerCo = firstPrzwnerCo!!,
        )
    }
}
