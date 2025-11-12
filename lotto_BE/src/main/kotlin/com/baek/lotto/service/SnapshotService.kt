package com.baek.lotto.service

import com.baek.lotto.domain.entity.StatsSnapshotEntity
import com.baek.lotto.domain.repository.StatsGlobalRepository
import com.baek.lotto.domain.repository.StatsRecentRepository
import com.baek.lotto.domain.repository.StatsSnapshotRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class SnapshotService(
    private val statsGlobalRepository: StatsGlobalRepository,
    private val statsRecentRepository: StatsRecentRepository,
    private val statsSnapshotRepository: StatsSnapshotRepository
) {
    private val mapper = jacksonObjectMapper()

    @Transactional
    fun updateSnapshot() {
        updateGlobalSnapshot()
        updateRecentSnapshot()
    }

    @Transactional
    fun updateGlobalSnapshot() {
        val global = statsGlobalRepository.findAllByOrderByCntAscNumberAsc()
        val bottom15 = global.takeLast(GLOBAL_BOTTOM_COUNT).map { it.number }
        val drwNo = global.firstOrNull()?.toDrw ?: 0

        upsert(GLOBAL_ID, bottom15, drwNo)
    }

    @Transactional
    private fun updateRecentSnapshot() {
        val recent = statsRecentRepository.findAllByOrderByCntDescNumberAsc()
        val top20 = recent.take(RECENT_TOP_COUNT).map { it.number }
        val drwNo = recent.firstOrNull()?.toDrw ?: 0

        upsert(RECENT_ID, top20, drwNo)
    }

    @Transactional
    private fun upsert(id: String, numbers: List<Int>, drwNo: Int) {
        val now = LocalDateTime.now()
        val json = mapper.writeValueAsString(numbers)

        val exists = statsSnapshotRepository.findById(id).orElse(null)
        if (exists == null) {
            statsSnapshotRepository.save(
                StatsSnapshotEntity(
                    id = id,
                    numbers = json,
                    currentDrwNo = drwNo,
                    updatedAt = now
                )
            )
            return
        }
        exists.numbers = json
        exists.currentDrwNo = drwNo
        exists.updatedAt = now
    }

    companion object {
        const val GLOBAL_ID = "global_bottom15"
        const val RECENT_ID = "recent_top20"
        const val GLOBAL_BOTTOM_COUNT = 15
        const val RECENT_TOP_COUNT = 20
    }
}
