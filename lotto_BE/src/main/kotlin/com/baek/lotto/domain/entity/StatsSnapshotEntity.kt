package com.baek.lotto.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Id
import java.time.LocalDateTime

class StatsSnapshotEntity (
    @Id
    @Column(name = "id", length = 50)
    val id: String, //recentTop20, globalBottom15

    @Column(name = "numbers", columnDefinition = "json", nullable = false)
    var numbers: String,

    @Column(name = "from_drw", nullable = false)
    var fromDrw: Int,

    @Column(name = "to_drw", nullable = false)
    var toDrw: Int,

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
)
