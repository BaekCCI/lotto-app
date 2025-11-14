package com.baek.lotto.domain.repository

import com.baek.lotto.data.model.DrawDto
import com.baek.lotto.data.model.RandomLottoDto
import com.baek.lotto.data.model.SyncResultDto

interface LottoRepository {

    suspend fun sync(): Result<SyncResultDto>

    suspend fun getDraw(drwNo:Int):Result<DrawDto>

    suspend fun getLatest():Result<DrawDto>

    suspend fun createRandomLotto(): Result<RandomLottoDto>
}