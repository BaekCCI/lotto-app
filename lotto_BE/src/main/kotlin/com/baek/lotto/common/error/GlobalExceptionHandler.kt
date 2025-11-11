package com.baek.lotto.common.error

import com.baek.lotto.common.response.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(LottoException::class)
    fun handleLottoException(e: LottoException): ResponseEntity<ApiResponse<Nothing>> {
        val type = e.errorType
        val body = ApiResponse.error(type)
        return ResponseEntity.status(type.status).body(body)
    }
}
