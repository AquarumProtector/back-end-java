package com.fiap.globalsolution1.controller;

import com.fiap.globalsolution1.dto.WaterSourceDTO;
import com.fiap.globalsolution1.model.WaterSource;
import com.fiap.globalsolution1.model.enums.WaterSourceType;
import com.fiap.globalsolution1.repository.WaterSourceRepository;
import com.fiap.globalsolution1.service.WaterSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/watersource")
public class WaterSourceController {

    @Autowired
    private WaterSourceService waterSourceService;

    @GetMapping
    public ResponseEntity<Page<WaterSourceDTO>> findAll(Pageable pageable){
        Page<WaterSourceDTO> waterSources = waterSourceService.findAll(pageable);
        return ResponseEntity.ok(waterSources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WaterSourceDTO> findById(@PathVariable Long id) {
        WaterSourceDTO waterSource = waterSourceService.findById(id);
        return ResponseEntity.ok(waterSource);
    }

    @GetMapping("/tipo/{type}")
    public ResponseEntity<Page<WaterSourceDTO>> findAllByType(@PathVariable WaterSourceType type, Pageable pageable){
        Page<WaterSourceDTO> waterSources = waterSourceService.findByType(type, pageable);
        return ResponseEntity.ok(waterSources);
    }

    @GetMapping("/ativo/{isActive}")
    public ResponseEntity<Page<WaterSourceDTO>> findAllByActive(@PathVariable boolean isActive, Pageable pageable){
        Page<WaterSourceDTO> waterSources = waterSourceService.findByActive(isActive, pageable);
        return ResponseEntity.ok(waterSources);
    }

    @PostMapping
    public ResponseEntity<WaterSource> save(@RequestBody WaterSource waterSource) {
        WaterSource waterSourceSaved = waterSourceService.save(waterSource);
        return ResponseEntity.status(HttpStatus.CREATED).body(waterSourceSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WaterSource> update(@PathVariable Long id, @RequestBody WaterSource waterSource) {
        WaterSource waterSourceSaved = waterSourceService.update(id, waterSource);
        return ResponseEntity.ok(waterSourceSaved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WaterSource> delete(@PathVariable Long id) {
        waterSourceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}