package com.baek.lotto.domain.repository

import com.baek.lotto.domain.entity.DrawEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface DrawRepository : JpaRepository<DrawEntity, Long> {

    //@Query("select from draws where drw_no = drwNo")
    fun findByDrwNo(drwNo: Int): DrawEntity?

    //회차 존재 확인
    fun existsByDrwNo(drwNo: Int): Boolean

    //최근 회차 번호 가져오기
    @Query("select max(d.drwNo) from DrawEntity d")
    fun findMaxDrwNo(): Int?

    //최신 회차 로또 정보
    fun findTopByOrderByDrwNoDesc(): DrawEntity?
}
