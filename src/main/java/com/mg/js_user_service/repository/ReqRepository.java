package com.mg.js_user_service.repository;

import com.mg.js_user_service.entity.ReqTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReqRepository extends JpaRepository<ReqTable, Long> {

  @Query(
          value = "SELECT * FROM req_table " +
                  "WHERE status = :status " +
                  "ORDER BY id ASC " +
                  "LIMIT 10 " +           // Fetch only 2 records
                  "FOR UPDATE SKIP LOCKED", // Skip locked rows
          nativeQuery = true
  )
  List<ReqTable> findNext2UnlockedByStatus(@Param("status") String status);
}
