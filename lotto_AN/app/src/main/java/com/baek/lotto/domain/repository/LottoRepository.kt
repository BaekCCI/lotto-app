package com.baek.lotto.domain.repository

import com.baek.lotto.domain.model.Draw
import com.baek.lotto.domain.model.RandomLotto
import com.baek.lotto.domain.model.SyncResult
import com.baek.lotto.common.Result

interface LottoRepository {

    suspend fun sync(): Result<SyncResult>

    suspend fun getDraw(drwNo: Int): Result<Draw>

    suspend fun getLatest(): Result<Draw>

    suspend fun createRandomLotto(count: Int): Result<List<RandomLotto>>
}