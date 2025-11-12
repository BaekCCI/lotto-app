package com.baek.lotto.domain.repository

import com.baek.lotto.domain.entity.StatsSnapshotEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface StatsSnapshotRepository : JpaRepository<StatsSnapshotEntity, String> {

    fun findTopByOrderByDrwNoDesc(): StatsSnapshotEntity?
}
