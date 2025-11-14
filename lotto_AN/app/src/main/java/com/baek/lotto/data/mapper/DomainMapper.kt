package com.baek.lotto.data.mapper

import com.baek.lotto.data.model.DrawDto
import com.baek.lotto.data.model.RandomLottoDto
import com.baek.lotto.data.model.SyncResultDto
import com.baek.lotto.domain.model.Draw
import com.baek.lotto.domain.model.RandomLotto
import com.baek.lotto.domain.model.SyncResult

object DomainMapper {

    fun DrawDto.toDomain(): Draw {
        return Draw(
            drawNo = drwNo,
            drawDate = drwDate,
            numbers = numbers,
            bonus = bonus,
            firstPrize = firstWinamnt,
            firstWinnerCount = firstPrzwnerCo
        )
    }

    fun RandomLottoDto.toDomain(): RandomLotto {
        return RandomLotto(
            numbers = numbers
        )
    }

    fun SyncResultDto.toDomain(): SyncResult {
        return SyncResult(
            fromDrawNo = fromDrw,
            toDrawNo = toDrw,
            syncedCount = saved
        )
    }
}