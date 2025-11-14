package com.baek.lotto.data.repository

import com.baek.lotto.data.model.DrawDto
import com.baek.lotto.data.model.RandomLottoDto
import com.baek.lotto.data.model.SyncResultDto
import com.baek.lotto.data.remote.LottoApi
import com.baek.lotto.domain.repository.LottoRepository
import javax.inject.Inject

class LottoRepositoryImpl @Inject constructor(
    private val api: LottoApi
) : LottoRepository {
    override suspend fun sync(): Result<SyncResultDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getDraw(drwNo: Int): Result<DrawDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getLatest(): Result<DrawDto> {
        TODO("Not yet implemented")
    }

    override suspend fun createRandomLotto(): Result<RandomLottoDto> {
        TODO("Not yet implemented")
    }
}