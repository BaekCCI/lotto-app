package com.baek.lotto.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Id
import java.time.LocalDateTime

class StatsRecentEntity (
    @Id
    @Column(name = "number", nullable = false)
    val number: Int,

    @Column(name = "cnt", nullable = false)
    var cnt: Int,

    @Column(name = "from_drw", nullable = false)
    var fromDrw: Int,

    @Column(name = "to_drw", nullable = false)
    var toDrw: Int,

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
)
