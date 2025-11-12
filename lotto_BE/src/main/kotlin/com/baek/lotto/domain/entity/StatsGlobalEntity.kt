package com.baek.lotto.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(
    name = "stats_global",
    indexes = [Index(name = "idx_global", columnList = "to_drw")],
)
class StatsGlobalEntity (
    @Id
    @Column(name = "number", nullable = false)
    val number: Int,

    @Column(name = "cnt", nullable = false)
    var cnt: Int,

    @Column(name = "to_drw", nullable = false)
    var toDrw: Int,

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
)
