package com.baek.lotto.service

import com.baek.lotto.common.constant.LottoConstant.LOTTO_MAX_NUMBER
import com.baek.lotto.common.constant.LottoConstant.LOTTO_MIN_NUMBER
import com.baek.lotto.common.constant.LottoConstant.LOTTO_SIZE
import com.baek.lotto.common.constant.RandomConstants.PICK_GLOBAL_COUNT
import com.baek.lotto.common.constant.RandomConstants.PICK_RECENT_COUNT
import com.baek.lotto.common.constant.SnapshotConstant.GLOBAL_ID
import com.baek.lotto.common.constant.SnapshotConstant.RECENT_ID
import com.baek.lotto.domain.repository.StatsSnapshotRepository
import com.baek.lotto.dto.RandomLottoDto
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.stereotype.Service

@Service
class RandomLottoService(
    private val statsSnapshotRepository: StatsSnapshotRepository
) {
    private val mapper = jacksonObjectMapper()

    fun generate(): RandomLottoDto {
        val recent20 = getNumbers(RECENT_ID)
        val global15 = getNumbers(GLOBAL_ID)

        val picked = mutableSetOf<Int>()
        picked.addAll(recent20.pickRandom(PICK_RECENT_COUNT))
        picked.addAll(global15.pickRandom(PICK_GLOBAL_COUNT))

        val need = LOTTO_SIZE - picked.size
        picked += getRandomNumbers(need, picked)

        return RandomLottoDto(picked.toList().sorted())
    }

    private fun getNumbers(id: String): List<Int> {
        val entity = statsSnapshotRepository.findById(id).orElse(null) ?: return emptyList()

        return mapper.readValue(entity.numbers, List::class.java)?.map { (it as Number).toInt() }
            ?: emptyList()
    }

    private fun List<Int>.pickRandom(count: Int): Set<Int> {
        return this.shuffled().take(count).toSet()
    }

    private fun getRandomNumbers(count: Int, exclude: Set<Int>): Set<Int> {
        val candidates = (LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER).filterNot { it in exclude }
        return candidates.shuffled().take(count).toSet()
    }
}
