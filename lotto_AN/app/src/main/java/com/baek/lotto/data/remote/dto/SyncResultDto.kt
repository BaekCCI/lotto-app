package com.baek.lotto.data.remote.dto

data class SyncResultDto(
    val fromDrw: Int,
    val toDrw: Int,
    val saved: Int
)