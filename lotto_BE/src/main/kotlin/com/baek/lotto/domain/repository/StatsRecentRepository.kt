package com.baek.lotto.domain.repository

import com.baek.lotto.domain.entity.StatsRecentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StatsRecentRepository : JpaRepository<StatsRecentEntity, Int> {

    fun findAllByOrderByCntDescNumberAsc(): List<StatsRecentEntity>
}
