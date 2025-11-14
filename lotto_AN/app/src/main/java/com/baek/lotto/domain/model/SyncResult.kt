package com.baek.lotto.domain.model

data class SyncResult(
    val fromDrawNo:Int,
    val toDrawNo:Int,
    val syncedCount:Int
)