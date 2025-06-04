package com.fiap.globalsolution1.service;

import com.fiap.globalsolution1.controller.WaterSourceUpdateController;
import com.fiap.globalsolution1.dto.WaterSourceUpdateDTO;
import com.fiap.globalsolution1.exception.ResourceNotFoundException;
import com.fiap.globalsolution1.model.WaterSourceUpdate;
import com.fiap.globalsolution1.model.enums.WaterSourceStatus;
import com.fiap.globalsolution1.repository.WaterSourceUpdateRepository;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class WaterSourceUpdateService {

    @Autowired
    private WaterSourceUpdateRepository waterSourceUpdateRepository;

    public Page<WaterSourceUpdateDTO> getWaterSourceUpdates(Pageable pageable) {
        Page<WaterSourceUpdate> waterSourceUpdates =  waterSourceUpdateRepository.findAll(pageable);
        if(waterSourceUpdates.isEmpty()){
            throw new ResourceNotFoundException("Nenhuma atualização para essa fonte de água registrada");
        }

        Page<WaterSourceUpdateDTO> dtoPage = waterSourceUpdates.map(waterSourceUpdate -> {
            WaterSourceUpdateDTO dto = new WaterSourceUpdateDTO(waterSourceUpdate);
            dto.add(linkTo(methodOn(WaterSourceUpdateController.class)
                    .findById(waterSourceUpdate.getId())).withSelfRel());
            return dto;
        });

        return dtoPage;
    }

    public Page<WaterSourceUpdateDTO> getWaterSourceUpdatesByStatus(WaterSourceStatus status, Pageable pageable) {
        Page<WaterSourceUpdate> waterSourceUpdates =  waterSourceUpdateRepository.findByStatus(status, pageable);
        if(waterSourceUpdates.isEmpty()){
            throw new ResourceNotFoundException("Nenhuma fonte de água registrada para esse filtro");
        }

        Page<WaterSourceUpdateDTO> dtoPage = waterSourceUpdates.map(waterSourceUpdate -> {
            WaterSourceUpdateDTO dto = new WaterSourceUpdateDTO(waterSourceUpdate);
            dto.add(linkTo(methodOn(WaterSourceUpdateController.class)
                    .findById(waterSourceUpdate.getId())).withSelfRel());
            return dto;
        });

        return dtoPage;
    }

    @Cacheable("waterSourceUpdate")
    public WaterSourceUpdateDTO getWaterSourceUpdateById(Long id) {
        WaterSourceUpdate update =  waterSourceUpdateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Atualização de fonte de água não encontrada"));
        WaterSourceUpdateDTO dto = new WaterSourceUpdateDTO(update);
        dto.add(linkTo(methodOn(WaterSourceUpdateController.class).findById(id)).withSelfRel());

        return dto;
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
