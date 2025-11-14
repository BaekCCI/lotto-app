package com.baek.lotto.controller

import com.baek.lotto.common.response.ApiResponse
import com.baek.lotto.dto.RandomLottoDto
import com.baek.lotto.dto.RandomLottoRequest
import com.baek.lotto.service.RandomLottoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RandomLottoController(
    private val randomLottoService: RandomLottoService
) {
    @PostMapping("/api/lotto/random")
    fun createRandomLotto(
        @RequestBody request: RandomLottoRequest
    ): ResponseEntity<ApiResponse<List<RandomLottoDto>>> {
        val result = randomLottoService.generate(request.count)
        return ResponseEntity.ok(ApiResponse.success(result))
    }
}
