package com.baek.lotto.common

data class ApiResponse<T>(
    val status: Int,
    val code: String,
    val message: String,
    val data: T?,
    val timestamp: String
)