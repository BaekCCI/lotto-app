package com.baek.lotto.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(
    name = "stats_recent_100",
    indexes = [Index(name = "idx_recent100", columnList = "to_drw")],
)
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
