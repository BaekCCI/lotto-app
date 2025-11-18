package com.baek.lotto.data.mapper

import com.baek.lotto.data.remote.dto.DrawDto
import com.baek.lotto.data.remote.dto.RandomLottoDto
import com.baek.lotto.data.remote.dto.SyncResultDto
import com.baek.lotto.domain.model.Draw
import com.baek.lotto.domain.model.RandomLotto
import com.baek.lotto.domain.model.SyncResult
import java.time.LocalDate

object DtoMapper {

    fun DrawDto.toDomain(): Draw {
        return Draw(
            drawNo = drwNo,
            drawDate = LocalDate.parse(drwDate),
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