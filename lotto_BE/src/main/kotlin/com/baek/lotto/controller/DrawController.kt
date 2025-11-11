package com.baek.lotto.controller

import com.baek.lotto.common.response.ApiResponse
import com.baek.lotto.dto.DrawDto
import com.baek.lotto.service.DrawService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class DrawController(
    val drawService: DrawService
) {

    @GetMapping("/api/draw/{drwNo}")
    fun getDraw(@PathVariable drwNo: Int): ResponseEntity<ApiResponse<DrawDto>> {
        val draw = drawService.getByDrwNo(drwNo)
        return ResponseEntity.ok(ApiResponse.success(draw, "${drwNo}회 로또 조회 성공"))
    }

    @GetMapping("/api/draw/latest")
    fun getLatestDraw(): ResponseEntity<ApiResponse<DrawDto>> {
        val draw = drawService.getLatestDrw()
        return ResponseEntity.ok(ApiResponse.success(draw, "최신 회차 로또 조회 성공"))
    }

    @GetMapping("/api/draw/sync")
    suspend fun fetch(): ResponseEntity<ApiResponse<Map<String, Int>>> {
        val saved = drawService.fetch()
        return ResponseEntity.ok(ApiResponse.success(mapOf("saved" to saved), "로또 동기화 성공"))
    }
}
