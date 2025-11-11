package com.baek.lotto.common.response

import com.baek.lotto.common.error.ErrorType
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ApiResponse<T>(
    val status: Int,
    val code: String,
    val message: String,
    val data: T? = null,
    val timestamp: LocalDateTime = LocalDateTime.now()
) {
    companion object {
        fun <T> success(data: T? = null, message: String = "정상 처리되었습니다."): ApiResponse<T> {
            return ApiResponse(
                status = HttpStatus.OK.value(),
                code = "SUCCESS",
                message = message,
                data = data
            )
        }

        fun error(type: ErrorType): ApiResponse<Nothing> {
            return ApiResponse(
                status = type.status.value(),
                code = type.name,
                message = type.message
            )
        }
    }
}
