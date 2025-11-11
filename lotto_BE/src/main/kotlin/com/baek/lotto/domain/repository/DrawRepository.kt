package com.baek.lotto.domain.repository

import com.baek.lotto.domain.entity.DrawEntity
import org.springframework.data.jpa.repository.JpaRepository

interface DrawRepository : JpaRepository<DrawEntity, Long> {

    //@Query("select from draws where drw_no = drwNo")
    fun findByDrwNo(drwNo: Int): DrawEntity?
}
