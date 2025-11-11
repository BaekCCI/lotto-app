package com.baek.lotto.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.time.Duration

@Configuration
class WebClientConfig {
    @Bean
    fun lottoWebClient(builder: WebClient.Builder): WebClient {
        val http = HttpClient.create().responseTimeout(Duration.ofSeconds(3))
        return builder
            .baseUrl("https://www.dhlottery.co.kr")
            .clientConnector(ReactorClientHttpConnector(http))
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .build()
    }
}
