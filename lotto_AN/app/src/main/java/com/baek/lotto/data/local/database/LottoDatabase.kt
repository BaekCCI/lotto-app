package com.baek.lotto.data.local.database

import androidx.room.Database
import com.baek.lotto.data.local.dao.LottoRecordDao
import com.baek.lotto.data.local.entity.LottoRecordEntity

@Database(entities = [LottoRecordEntity::class], version = 1)
abstract class LottoDatabase {

    abstract fun lottoRecordDao(): LottoRecordDao
}