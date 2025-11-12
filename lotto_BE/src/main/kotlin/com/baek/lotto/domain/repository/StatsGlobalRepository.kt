package com.baek.lotto.domain.repository

import com.baek.lotto.domain.entity.StatsGlobalEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StatsGlobalRepository : JpaRepository<StatsGlobalEntity, Int> {

    fun findAllByOrderByCntDescNumberAsc(): List<StatsGlobalEntity>
}
