package com.baek.lotto.service

import com.baek.lotto.common.error.ErrorType
import com.baek.lotto.common.error.LottoException
import com.baek.lotto.dto.DrawDto
import com.baek.lotto.domain.repository.DrawRepository
import com.baek.lotto.external.LottoApiClient
import com.baek.lotto.external.LottoApiMapper.toEntity
import com.baek.lotto.service.mapper.DrawMapper.toDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DrawService(
    private val drawRepository: DrawRepository,
    private val client: LottoApiClient
) {
    @Transactional(readOnly = true)
    fun getByDrwNo(drwNo: Int): DrawDto {
        val draw = drawRepository.findByDrwNo(drwNo)
            ?: throw LottoException(ErrorType.DRAW_NOT_FOUND)

        return draw.toDto()
    }

    @Transactional
    fun getLatestDrw(): DrawDto {
        val latest = drawRepository.findTopByOrderByDrwNoDesc()
            ?: throw LottoException(ErrorType.DRAW_NOT_FOUND)
        return latest.toDto()
    }

    @Transactional
    suspend fun fetch(): Int {
        val dbLatest = drawRepository.findMaxDrwNo() ?: 0
        val apiLatest = client.getLatestDrwNo(dbLatest + 1)
        if (apiLatest < dbLatest) {
            throw LottoException(ErrorType.FETCH_FAILED)
        }
        if (dbLatest == apiLatest) return 0
        var savedCount = 0

        for (n in (dbLatest + 1)..apiLatest) {
            val dto = client.getDraw(n)
                ?: throw LottoException(ErrorType.FETCH_FAILED)

            drawRepository.save(dto.toEntity())
            savedCount++
        }
        return savedCount
    }
}
