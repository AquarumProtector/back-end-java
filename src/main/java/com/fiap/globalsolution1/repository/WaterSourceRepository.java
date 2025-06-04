package com.fiap.globalsolution1.repository;

import com.fiap.globalsolution1.model.WaterSource;
import com.fiap.globalsolution1.model.enums.WaterSourceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WaterSourceRepository extends JpaRepository<WaterSource, Long> {

    @Query("SELECT w FROM WaterSource w WHERE w.type = :type")
    Page<WaterSource> buscarWaterSourcePorType(@Param("type") WaterSourceType type, Pageable pageable);

    @Query(value = "SELECT * FROM WaterSource WHERE is_active = :isActive", nativeQuery = true)
    Page<WaterSource> buscarWaterSourcePorActive(@Param("isActive") boolean isActive, Pageable pageable);
}
