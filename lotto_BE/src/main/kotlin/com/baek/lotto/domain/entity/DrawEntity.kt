package com.baek.lotto.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(
    name = "draw",
    indexes = [Index(name = "idx_draw", columnList = "drw_no", unique = true)]
)
class DrawEntity(
    @Id
    @Column(name = "drw_no") //회차
    val drwNo: Int,

    @Column(name = "drw_date", nullable = false) //추첨 날짜
    val drwDate: LocalDate,

    @Column(name = "n1", nullable = false)
    val n1: Int,

    @Column(name = "n2", nullable = false)
    val n2: Int,

    @Column(name = "n3", nullable = false)
    val n3: Int,

    @Column(name = "n4", nullable = false)
    val n4: Int,

    @Column(name = "n5", nullable = false)
    val n5: Int,

    @Column(name = "n6", nullable = false)
    val n6: Int,

    @Column(name = "bonus", nullable = false)
    val bonus: Int,

    @Column(name = "first_winamnt", nullable = false) //1등 당첨 금액
    val firstWinamnt: Long,

    @Column(name = "first_przwner_co", nullable = false) //1등 당첨자 수
    val firstPrzwnerCo: Int,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)
