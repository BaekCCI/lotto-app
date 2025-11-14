package com.baek.lotto.common

sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()

    data class Error(val message: String? = null, val throwable: Throwable? = null) :
        Result<Nothing>()

    data object Loading : Result<Nothing>()

    data object None : Result<Nothing>()
}