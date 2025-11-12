package com.baek.lotto.domain.repository

import com.baek.lotto.domain.entity.DrawEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

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

    //JPA 프로젝션 기능 활용을 위한 interface
    interface NumberCount {
        val number: Int
        val cnt: Int
    }

    @Query(
        value = """
        SELECT number, COUNT(*) AS cnt
        FROM (
          SELECT n1 AS number FROM draws WHERE drw_no BETWEEN :from AND :to
          UNION ALL SELECT n2 FROM draws WHERE drw_no BETWEEN :from AND :to
          UNION ALL SELECT n3 FROM draws WHERE drw_no BETWEEN :from AND :to
          UNION ALL SELECT n4 FROM draws WHERE drw_no BETWEEN :from AND :to
          UNION ALL SELECT n5 FROM draws WHERE drw_no BETWEEN :from AND :to
          UNION ALL SELECT n6 FROM draws WHERE drw_no BETWEEN :from AND :to
        ) t
        GROUP BY number
        ORDER BY cnt DESC, number ASC
        """,
        nativeQuery = true
    )
    fun countNumbersInRange(
        @Param("from") from: Int,
        @Param("to") to: Int
    ): List<NumberCount>
}
