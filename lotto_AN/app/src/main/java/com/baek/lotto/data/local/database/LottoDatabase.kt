package com.baek.lotto.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.baek.lotto.data.local.dao.LottoRecordDao
import com.baek.lotto.data.local.entity.LottoRecordEntity

@Database(entities = [LottoRecordEntity::class], version = 1, exportSchema = false)
abstract class LottoDatabase : RoomDatabase() {

    abstract fun lottoRecordDao(): LottoRecordDao
}