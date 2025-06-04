package com.fiap.globalsolution1.repository;

import com.fiap.globalsolution1.model.WaterSourceUpdate;
import com.fiap.globalsolution1.model.enums.WaterSourceStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WaterSourceUpdateRepository extends JpaRepository<WaterSourceUpdate, Long> {
    @Query("SELECT w FROM WaterSourceUpdate w WHERE w.status = :status")
    Page<WaterSourceUpdate> findByStatus(@Param("status") WaterSourceStatus status, Pageable pageable);
}