package com.fiap.globalsolution1.service;

import com.fiap.globalsolution1.exception.ResourceNotFoundException;
import com.fiap.globalsolution1.model.WaterSource;
import com.fiap.globalsolution1.repository.WaterSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WaterSourceService {

    @Autowired
    private WaterSourceRepository waterSourceRepository;

    public Page<WaterSource> findAll(Pageable pageable) {
        return waterSourceRepository.findAll(pageable);
    }

    @Cacheable("waterSource")
    public WaterSource findById(Long id){
        return waterSourceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fonte de água não encontrada"));
    }

    public WaterSource save(WaterSource waterSource) {
        return waterSourceRepository.save(waterSource);
    }

    public WaterSource update(Long id, WaterSource waterSource) {
        WaterSource waterSourceToUpdate = waterSourceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fonte de água não encontrada"));

        waterSourceToUpdate.setDescricao(waterSource.getDescricao());waterSourceToUpdate.setDescricao(waterSource.getDescricao());
        waterSourceToUpdate.setNome(waterSource.getNome());
        waterSourceToUpdate.setLocalizacao(waterSource.getLocalizacao());
        waterSourceToUpdate.setLatitude(waterSource.getLatitude());
        waterSourceToUpdate.setLongitude(waterSource.getLongitude());
        waterSourceToUpdate.setType(waterSource.getType());
        waterSourceToUpdate.setCreatedById(waterSource.getCreatedById());
        waterSourceToUpdate.setLastInspected(waterSource.getLastInspected());
        waterSourceToUpdate.setActive(waterSource.isActive());
        waterSourceToUpdate.setStatus(waterSource.getStatus());
        waterSourceToUpdate.setUpdatedAt(LocalDateTime.now());

        waterSourceToUpdate.setWaterSourceUpdates(waterSource.getWaterSourceUpdates());

        return waterSourceRepository.save(waterSourceToUpdate);
    }

    public void delete(Long id){
        if(!waterSourceRepository.existsById(id)){
            throw new ResourceNotFoundException("Fonte de água não encontrada");
        }
        waterSourceRepository.deleteById(id);
    }
}
