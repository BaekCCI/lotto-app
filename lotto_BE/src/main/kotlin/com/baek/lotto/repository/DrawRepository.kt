package com.baek.lotto.repository

import com.baek.lotto.domain.entity.DrawEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface DrawRepository : JpaRepository<DrawEntity, Long> {

    //@Query("select from draws where drw_no = drwNo")
    fun findByDrwNo(drwNo: Int): DrawEntity?
}
