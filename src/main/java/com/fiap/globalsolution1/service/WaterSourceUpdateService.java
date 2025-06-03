package com.fiap.globalsolution1.service;

import com.fiap.globalsolution1.exception.ResourceNotFoundException;
import com.fiap.globalsolution1.model.WaterSourceUpdate;
import com.fiap.globalsolution1.repository.WaterSourceUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WaterSourceUpdateService {

    @Autowired
    private WaterSourceUpdateRepository waterSourceUpdateRepository;

    public Page<WaterSourceUpdate> getWaterSourceUpdates(Pageable pageable) {
        return waterSourceUpdateRepository.findAll(pageable);
    }

    public WaterSourceUpdate getWaterSourceUpdateById(Long id) {
        return waterSourceUpdateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Atualização de fonte de água não encontrada"));
    }

    public WaterSourceUpdate saveWaterSourceUpdate(WaterSourceUpdate waterSourceUpdate) {
        return waterSourceUpdateRepository.save(waterSourceUpdate);
    }

    public WaterSourceUpdate updateWaterSourceUpdate(WaterSourceUpdate waterSourceUpdate, Long id) {
        WaterSourceUpdate update = waterSourceUpdateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Atualização de fonte de água não encontrada"));

        update.setUpdateDate(waterSourceUpdate.getUpdateDate());
        update.setDescricao(waterSourceUpdate.getDescricao());
        update.setOldStatus(waterSourceUpdate.getOldStatus());
        update.setStatus(waterSourceUpdate.getStatus());
        update.setLatitude(waterSourceUpdate.getLatitude());
        update.setLongitude(waterSourceUpdate.getLongitude());

        return waterSourceUpdateRepository.save(update);
    }

    public void deleteWaterSourceUpdate(Long id) {
        if(!waterSourceUpdateRepository.existsById(id)) {
            throw new ResourceNotFoundException("Atualização de fonte de água não encontrada");
        }
        waterSourceUpdateRepository.deleteById(id);
    }
}
