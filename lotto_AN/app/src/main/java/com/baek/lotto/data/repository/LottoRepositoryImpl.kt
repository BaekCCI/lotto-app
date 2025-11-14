package com.baek.lotto.data.repository

import com.baek.lotto.data.remote.LottoApi
import com.baek.lotto.domain.repository.LottoRepository
import com.baek.lotto.domain.model.Draw
import com.baek.lotto.domain.model.RandomLotto
import com.baek.lotto.domain.model.SyncResult
import javax.inject.Inject

class LottoRepositoryImpl @Inject constructor(
    private val api: LottoApi
) : LottoRepository {
    override suspend fun sync(): Result<SyncResult> {
        TODO("Not yet implemented")
    }

    override suspend fun getDraw(drwNo: Int): Result<Draw> {
        TODO("Not yet implemented")
    }

    override suspend fun getLatest(): Result<Draw> {
        TODO("Not yet implemented")
    }

    override suspend fun createRandomLotto(): Result<RandomLotto> {
        TODO("Not yet implemented")
    }
}