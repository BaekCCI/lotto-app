package com.baek.lotto.service.mapper

import com.baek.lotto.domain.entity.DrawEntity
import com.baek.lotto.dto.DrawDto

object DrawMapper {

    fun DrawEntity.toDto(): DrawDto {
        return DrawDto(
            drwNo = drwNo,
            drwDate = drwDate,
            numbers = listOf(n1,n2,n3,n4,n5,n6),
            bonus = bonus,
            firstWinamnt = firstWinamnt,
            firstPrzwnerCo = firstPrzwnerCo
        )
    }
}
