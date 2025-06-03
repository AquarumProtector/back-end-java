package com.fiap.globalsolution1.controller;

import com.fiap.globalsolution1.model.WaterSource;
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
    public ResponseEntity<Page<WaterSource>> findAll(Pageable pageable){
        Page<WaterSource> waterSources = waterSourceService.findAll(pageable);
        return ResponseEntity.ok(waterSources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WaterSource> findById(@PathVariable Long id) {
        WaterSource waterSource = waterSourceService.findById(id);
        return ResponseEntity.ok(waterSource);
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
