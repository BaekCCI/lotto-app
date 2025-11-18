package com.baek.lotto.data.repository

import com.baek.lotto.common.Result
import com.baek.lotto.data.local.dao.LottoRecordDao
import com.baek.lotto.data.mapper.EntityMapper.toDomain
import com.baek.lotto.data.mapper.EntityMapper.toEntity
import com.baek.lotto.domain.model.LottoRecord
import com.baek.lotto.domain.model.RandomLotto
import com.baek.lotto.domain.repository.StorageRepository
import java.time.LocalDate
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val dao: LottoRecordDao
) : StorageRepository {
    override suspend fun saveLotto(lottos: List<RandomLotto>): Result<Unit> {
        return handleDao {
            val now = LocalDate.now()
            val records = lottos.map { it.toEntity(now) }

            dao.insertAll(records)
        }
    }

    override suspend fun getLottoHistory(): Result<List<LottoRecord>> {
        return handleDao {
            val result = dao.getAll()
            result.map { it.toDomain() }
        }
    }

    override suspend fun deleteLotto(ids: List<Long>): Result<Unit> {
        return handleDao {
            dao.deleteAll(ids)
        }
    }

    override suspend fun deleteAll(): Result<Unit> {
        return handleDao {
            dao.clear()
        }
    }

    private inline fun <T> handleDao(block: () -> T): Result<T> {
        return try {
            Result.Success(block())
        } catch (e: Exception) {
            Result.Error(e.message, e)
        }
    }
}