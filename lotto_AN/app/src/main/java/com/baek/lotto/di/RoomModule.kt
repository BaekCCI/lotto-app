package com.baek.lotto.di

import android.content.Context
import androidx.room.Room
import com.baek.lotto.data.local.dao.LottoRecordDao
import com.baek.lotto.data.local.database.LottoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideLottoDatabase(
        @ApplicationContext context: Context
    ): LottoDatabase {
        return Room.databaseBuilder(
            context,
            LottoDatabase::class.java,
            "lotto_storage"
        ).build()
    }

    @Provides
    fun provideLottoRecordDao(db: LottoDatabase): LottoRecordDao {
        return db.lottoRecordDao()
    }
}