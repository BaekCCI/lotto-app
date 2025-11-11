package com.baek.lotto.service

import com.baek.lotto.dto.DrawDto
import com.baek.lotto.repository.DrawRepository
import com.baek.lotto.service.mapper.DrawMapper.toDto
import org.springframework.stereotype.Service

@Service
class DrawService(
    val drawRepository: DrawRepository
) {

    fun getByDrwNo(drwNo: Int): DrawDto? {
        return drawRepository.findByDrwNo(drwNo)?.toDto()
    }

}
