package com.baek.lotto.data.repository

import com.baek.lotto.data.remote.LottoApi
import com.baek.lotto.domain.repository.LottoRepository
import com.baek.lotto.domain.model.Draw
import com.baek.lotto.domain.model.RandomLotto
import com.baek.lotto.domain.model.SyncResult
import javax.inject.Inject
import com.baek.lotto.common.Result
import com.baek.lotto.data.mapper.DomainMapper.toDomain
import com.baek.lotto.data.remote.dto.RandomLottoRequest

class LottoRepositoryImpl @Inject constructor(
    private val api: LottoApi
) : LottoRepository {
    override suspend fun sync(): Result<SyncResult> {
        return handleApi {
            val response = api.sync()
            val data = response.data ?: throw NullPointerException("데이터가 비어있습니다.")
            data.toDomain()
        }
    }

    override suspend fun getDraw(drwNo: Int): Result<Draw> {
        return handleApi {
            val response = api.getDraw(drwNo)
            val data = response.data ?: throw NullPointerException("데이터가 비어있습니다.")
            data.toDomain()
        }
    }

    override suspend fun getLatest(): Result<Draw> {
        return handleApi {
            val response = api.getLatestDraw()
            val data = response.data ?: throw NullPointerException("데이터가 비어있습니다.")
            data.toDomain()
        }
    }

    override suspend fun createRandomLotto(count: Int): Result<List<RandomLotto>> {
        return handleApi {
            val response = api.createRandomLottos(RandomLottoRequest(count))
            val data = response.data ?: throw NullPointerException("데이터가 비어있습니다.")
            data.map { it.toDomain() }
        }
    }

    private inline fun <T> handleApi(block: () -> T): Result<T> {
        return try {
            Result.Success(block())
        } catch (e: Exception) {
            Result.Error(e.message, e)
        }
    }
}