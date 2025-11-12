package com.baek.lotto.service

import com.baek.lotto.dto.SyncResultDto
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class SyncService(
    private val drawService: DrawService,
    private val statsService: StatsService,
    private val snapshotService: SnapshotService
) {

    @Transactional
    suspend fun sync(): SyncResultDto {
        val result = drawService.syncDrw() ?: return SyncResultDto(0, 0, 0)

        statsService.updateRecent()
        statsService.updateGlobal(result.fromDrw, result.toDrw)
        snapshotService.updateSnapshot()

        return result
    }
}
