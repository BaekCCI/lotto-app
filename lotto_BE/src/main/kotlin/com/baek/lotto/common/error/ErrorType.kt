package com.baek.lotto.common.error

import org.springframework.http.HttpStatus

enum class ErrorType(val status: HttpStatus, val message: String) {
    DRAW_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 회차의 로또 정보를 찾을 수 없습니다."),
    FETCH_FAILED(HttpStatus.BAD_REQUEST, "로또 정보를 불러오는 중 오류가 발생했습니다.")
}
