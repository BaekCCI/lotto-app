package com.baek.lotto.data.remote

import com.baek.lotto.common.ApiResponse
import com.baek.lotto.data.model.DrawDto
import com.baek.lotto.data.model.RandomLottoDto
import com.baek.lotto.data.model.RandomLottoRequest
import com.baek.lotto.data.model.SyncResultDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LottoApi {

    @POST("api/draw/sync")
    suspend fun sync(): ApiResponse<SyncResultDto>

    @GET("api/draw/{drwNo}")
    suspend fun getDraw(
        @Path("drwNo") drwNo: Int
    ): ApiResponse<DrawDto>

    @GET("api/draw/latest")
    suspend fun getLatestDraw(): ApiResponse<DrawDto>

    @POST("api/lotto/random")
    suspend fun createRandomLottos(
        @Body request: RandomLottoRequest
    ): ApiResponse<List<RandomLottoDto>>
}
