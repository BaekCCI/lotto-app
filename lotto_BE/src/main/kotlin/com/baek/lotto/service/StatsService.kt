package com.baek.lotto.service

import com.baek.lotto.common.constant.LottoConstant.LOTTO_MAX_NUMBER
import com.baek.lotto.common.constant.LottoConstant.LOTTO_MIN_NUMBER
import com.baek.lotto.domain.entity.StatsGlobalEntity
import com.baek.lotto.domain.entity.StatsRecentEntity
import com.baek.lotto.domain.repository.DrawRepository
import com.baek.lotto.domain.repository.StatsGlobalRepository
import com.baek.lotto.domain.repository.StatsRecentRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class StatsService(
    private val drawRepository: DrawRepository,
    private val statsGlobalRepository: StatsGlobalRepository,
    private val statsRecentRepository: StatsRecentRepository
) {
    private val mapper = jacksonObjectMapper()

    @Transactional
    fun updateGlobal(fromDrw: Int = 1, toDrw: Int) {
        val added = drawRepository.countNumbersInRange(fromDrw, toDrw)
        if (added.isEmpty()) return

        val addedMap = added.associateBy({ it.number }, { it.cnt })
        val now = LocalDateTime.now()

        for (n in LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER) {
            val entity = statsGlobalRepository.findById(n).orElse(
                StatsGlobalEntity(
                    number = n,
                    cnt = 0,
                    toDrw = toDrw,
                    updatedAt = now
                )
            )
            entity.cnt += (addedMap[n] ?: 0)
            entity.toDrw = toDrw
            entity.updatedAt = now
            statsGlobalRepository.save(entity)
        }
    }

    @Transactional
    fun updateRecent() {
        val max = drawRepository.findMaxDrwNo() ?: return

        val from = (max - 99).coerceAtLeast(1)
        val recentCounts = drawRepository.countNumbersInRange(from, max)

        val countMap = recentCounts.associateBy({ it.number }, { it.cnt })
        val now = LocalDateTime.now()

        statsRecentRepository.deleteAllInBatch()
        val toSave = (LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER).map { n ->
            StatsRecentEntity(
                number = n,
                cnt = countMap[n] ?: 0,
                fromDrw = from,
                toDrw = max,
                updatedAt = now
            )
        }
        statsRecentRepository.saveAll(toSave)
    }
}
