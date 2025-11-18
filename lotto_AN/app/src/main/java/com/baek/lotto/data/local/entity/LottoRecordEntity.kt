package com.baek.lotto.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lotto_storage")
data class LottoRecordEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val numbers: String, //1,2,3,4,5,6
    val createdAt: String
)