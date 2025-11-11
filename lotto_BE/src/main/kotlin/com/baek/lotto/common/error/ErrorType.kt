package com.baek.lotto.common.error

import org.springframework.http.HttpStatus

enum class ErrorType(val status: HttpStatus, val message: String) {
    DRAW_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 회차의 로또 정보를 찾을 수 없습니다.")
}
