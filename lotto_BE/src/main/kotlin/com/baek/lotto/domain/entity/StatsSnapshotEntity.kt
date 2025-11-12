package com.baek.lotto.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(
    name = "stats_snapshot",
    indexes = [Index(name = "idx_snapshot", columnList = "drw_no", unique = true)],
)
class StatsSnapshotEntity(
    @Id
    @Column(name = "drw_no")
    val drwNo: Int,

    @Column(name = "recent_top20", columnDefinition = "json", nullable = false)
    var recentTop20: String,

    @Column(name = "global_bottom15", columnDefinition = "json", nullable = false)
    var globalBottom15: String,

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
)
