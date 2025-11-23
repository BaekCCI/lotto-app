package com.baek.lotto

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class LottoApplication

fun main(args: Array<String>) {
    runApplication<LottoApplication>(*args)
}
