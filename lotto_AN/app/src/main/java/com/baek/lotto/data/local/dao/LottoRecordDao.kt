package com.baek.lotto.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.baek.lotto.data.local.entity.LottoRecordEntity

@Dao
interface LottoRecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(record: List<LottoRecordEntity>)

    @Query("SELECT * FROM lotto_storage ORDER BY createdAt DESC")
    suspend fun getAll(): List<LottoRecordEntity>

    @Query("DELETE FROM lotto_storage WHERE id IN (:ids)")
    suspend fun deleteAll(ids: List<Long>)

    @Query("DELETE FROM lotto_storage")
    suspend fun clear()
}