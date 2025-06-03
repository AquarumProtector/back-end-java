package com.fiap.globalsolution1.repository;

import com.fiap.globalsolution1.model.WaterSource;
import com.fiap.globalsolution1.model.WaterSourceUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaterSourceUpdateRepository extends JpaRepository<WaterSourceUpdate, Long> {
}
