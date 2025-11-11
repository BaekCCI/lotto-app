package com.baek.lotto.external

import kotlinx.coroutines.withTimeout
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class LottoApiClient(
    private val webClient: WebClient
) {

    suspend fun getLatestDrwNo(start: Int = 1100): Int {
        var cur = start
        var lastSuccess = start - 1
        while (cur - start < 1500) {
            val ok = runCatching { getDraw(cur) != null }.getOrDefault(false)
            if (!ok) break

            lastSuccess = cur
            cur++
        }
        return lastSuccess
    }


    suspend fun getDraw(drwNo: Int): LottoApiDto? = runCatching {
        withTimeout(3000) {
            webClient.get()
                .uri { b ->
                    b.path("/common.do")
                        .queryParam("method", "getLottoNumber")
                        .queryParam("drwNo", drwNo)
                        .build()
                }
                .retrieve()
                .onStatus({ it.isError }) { resp ->
                    resp.bodyToMono(String::class.java).map { RuntimeException("HTTP ${resp.statusCode()} $it") }
                }
                .awaitBody<LottoApiDto>()
        }
    }.getOrNull()?.takeIf { it.returnValue == "success" }
}
