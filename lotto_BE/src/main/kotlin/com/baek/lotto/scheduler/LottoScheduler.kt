package com.baek.lotto.scheduler

import com.baek.lotto.service.SyncService
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class LottoScheduler(
    private val syncService: SyncService
) {
    private val Log = LoggerFactory.getLogger(LottoScheduler::class.java)

    @Volatile
    private var needRetry: Boolean = false

    @Scheduled(cron = "0 30 21 ? * SAT", zone = "Asia/Seoul")
    fun syncDraw() = runBlocking {
        Log.info("$TAG 로또 동기화 실행")
        runSync()
    }

    @Scheduled(cron = "0 0/30 22-23 ? * SAT", zone = "Asia/Seoul")
    fun retrySync() = runBlocking {
        if (!needRetry) return@runBlocking

        Log.info("$TAG 로또 동기화 재시도")
        runSync()
    }

    private suspend fun runSync() {
        try {
            syncService.sync()
            needRetry = false

            Log.info("$TAG 로또 동기화 성공")
        } catch (e: Exception) {
            needRetry = true
            Log.error("$TAG 로또 동기화 실패", e)
        }
    }

    companion object {
        private const val TAG = "[LottoScheduler]"
    }
}
