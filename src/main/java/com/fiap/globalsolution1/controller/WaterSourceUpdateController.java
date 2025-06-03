package com.fiap.globalsolution1.controller;

import com.fiap.globalsolution1.model.WaterSourceUpdate;
import com.fiap.globalsolution1.service.WaterSourceUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/watersourceupdate")
public class WaterSourceUpdateController {

    @Autowired
    private WaterSourceUpdateService waterSourceUpdateService;

    @GetMapping
    public ResponseEntity<Page<WaterSourceUpdate>> getAllWaterSourceUpdates(Pageable pageable) {
        Page<WaterSourceUpdate> updates = waterSourceUpdateService.getWaterSourceUpdates(pageable);
        return ResponseEntity.ok(updates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WaterSourceUpdate> getWaterSource(@PathVariable Long id) {
        WaterSourceUpdate waterSourceUpdate = waterSourceUpdateService.getWaterSourceUpdateById(id);
        return ResponseEntity.ok(waterSourceUpdate);
    }

    @PostMapping
    public ResponseEntity<WaterSourceUpdate> createWaterSource(@RequestBody WaterSourceUpdate waterSourceUpdate) {
        WaterSourceUpdate saved = waterSourceUpdateService.saveWaterSourceUpdate(waterSourceUpdate);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WaterSourceUpdate> updateWaterSource(@PathVariable Long id, @RequestBody WaterSourceUpdate waterSourceUpdate) {
        WaterSourceUpdate uptaded = waterSourceUpdateService.updateWaterSourceUpdate(waterSourceUpdate, id);
        return ResponseEntity.ok(uptaded);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWaterSource(@PathVariable Long id) {
        waterSourceUpdateService.deleteWaterSourceUpdate(id);
        return ResponseEntity.noContent().build();
    }
}