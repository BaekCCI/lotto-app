package com.baek.lotto.domain.repository

import com.baek.lotto.domain.entity.StatsSnapshotEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StatsSnapshotRepository : JpaRepository<StatsSnapshotEntity, String>
