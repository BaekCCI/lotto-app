package com.baek.lotto.di

import com.baek.lotto.data.repository.LottoRepositoryImpl
import com.baek.lotto.data.repository.StorageRepositoryImpl
import com.baek.lotto.domain.repository.LottoRepository
import com.baek.lotto.domain.repository.StorageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindLottoRepository(
        lottoRepositoryImpl: LottoRepositoryImpl
    ): LottoRepository

    @Binds
    @Singleton
    abstract fun bindStorageRepository(
        storageRepositoryImpl: StorageRepositoryImpl
    ): StorageRepository
}
