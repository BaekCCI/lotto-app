package com.baek.lotto.domain.repository

import com.baek.lotto.common.Result
import com.baek.lotto.domain.model.LottoRecord
import com.baek.lotto.domain.model.RandomLotto

interface StorageRepository {

    suspend fun saveLotto(lottos: List<RandomLotto>): Result<Unit>

    suspend fun getLottoHistory(): Result<List<LottoRecord>>

    suspend fun deleteLotto(ids: List<Long>): Result<Unit>

    suspend fun deleteAll(): Result<Unit>
}