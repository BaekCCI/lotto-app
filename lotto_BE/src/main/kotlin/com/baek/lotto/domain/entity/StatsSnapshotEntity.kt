package com.baek.lotto.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(
    name = "stats_snapshot"
)
class StatsSnapshotEntity(
    @Id
    @Column(name = "id", length = 50)
    val id: String, //recentTop20, globalBottom15

    @Column(name = "numbers", columnDefinition = "json", nullable = false)
    var numbers: String,

    @Column(name = "current_drw_no", nullable = false)
    var currentDrwNo: Int,

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
)
