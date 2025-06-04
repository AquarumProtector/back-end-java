package com.fiap.globalsolution1.service;

import com.fiap.globalsolution1.controller.WaterSourceController;
import com.fiap.globalsolution1.dto.WaterSourceDTO;
import com.fiap.globalsolution1.exception.ResourceNotFoundException;
import com.fiap.globalsolution1.model.WaterSource;
import com.fiap.globalsolution1.model.enums.WaterSourceType;
import com.fiap.globalsolution1.repository.WaterSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class WaterSourceService {

    @Autowired
    private WaterSourceRepository waterSourceRepository;

    public Page<WaterSourceDTO> findAll(Pageable pageable) {
        Page<WaterSource> waterSources = waterSourceRepository.findAll(pageable);
        if(waterSources.isEmpty()){
            throw new ResourceNotFoundException("Nenhuma fonte de água registrada");
        }

        Page<WaterSourceDTO> dtoPage = waterSources.map(waterSource -> {
            WaterSourceDTO dto = new WaterSourceDTO(waterSource);
            dto.add(linkTo(methodOn(WaterSourceController.class)
                    .findById(waterSource.getId())).withSelfRel());
            return dto;
        });

        return dtoPage;
    }

    public Page<WaterSourceDTO> findByType(WaterSourceType type, Pageable pageable) {
        Page<WaterSource> waterSources= waterSourceRepository.buscarWaterSourcePorType(type, pageable);
        if(waterSources.isEmpty()){
            throw new ResourceNotFoundException("Nenhuma fonte de água registrada para esse filtro");
        }

        Page<WaterSourceDTO> dtoPage = waterSources.map(waterSource -> {
            WaterSourceDTO dto = new WaterSourceDTO(waterSource);
            dto.add(linkTo(methodOn(WaterSourceController.class)
                    .findById(waterSource.getId())).withSelfRel());
            return dto;
        });

        return dtoPage;
    }

    public Page<WaterSourceDTO> findByActive(Boolean isActive, Pageable pageable) {
        Page<WaterSource> waterSources = waterSourceRepository.buscarWaterSourcePorActive(isActive, pageable);
        if(waterSources.isEmpty()){
            throw new ResourceNotFoundException("Nenhuma fonte de água registrada para esse filtro");
        }

        Page<WaterSourceDTO> dtoPage = waterSources.map(waterSource -> {
            WaterSourceDTO dto = new WaterSourceDTO(waterSource);
            dto.add(linkTo(methodOn(WaterSourceController.class)
                    .findById(waterSource.getId())).withSelfRel());
            return dto;
        });

        return dtoPage;
    }

    @Cacheable("waterSource")
    public WaterSourceDTO findById(Long id){
        WaterSource waterSource =  waterSourceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fonte de água não encontrada"));
        WaterSourceDTO dto = new WaterSourceDTO(waterSource);
        dto.add(linkTo(methodOn(WaterSourceController.class).findById(id)).withSelfRel());
        return dto;
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
        waterSourceToUpdate.setLastInspected(waterSource.getLastInspected());
        waterSourceToUpdate.setActive(waterSource.isActive());
        waterSourceToUpdate.setStatus(waterSource.getStatus());
        waterSourceToUpdate.setUpdatedAt(LocalDateTime.now());

        return waterSourceRepository.save(waterSourceToUpdate);
    }

    public void delete(Long id){
        if(!waterSourceRepository.existsById(id)){
            throw new ResourceNotFoundException("Fonte de água não encontrada");
        }
        waterSourceRepository.deleteById(id);
    }
}
