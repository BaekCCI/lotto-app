package com.baek.lotto.service

import com.baek.lotto.common.error.ErrorType
import com.baek.lotto.common.error.LottoException
import com.baek.lotto.dto.DrawDto
import com.baek.lotto.domain.repository.DrawRepository
import com.baek.lotto.service.mapper.DrawMapper.toDto
import org.springframework.stereotype.Service

@Service
class DrawService(
    val drawRepository: DrawRepository
) {
    fun getByDrwNo(drwNo: Int): DrawDto {
        val draw = drawRepository.findByDrwNo(drwNo)
            ?: throw LottoException(ErrorType.DRAW_NOT_FOUND)

        return draw.toDto()
    }
}
