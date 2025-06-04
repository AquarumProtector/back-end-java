package com.fiap.globalsolution1.dto;

import com.fiap.globalsolution1.model.WaterSource;
import com.fiap.globalsolution1.model.WaterSourceUpdate;
import com.fiap.globalsolution1.model.enums.WaterSourceStatus;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
public class WaterSourceUpdateDTO extends RepresentationModel<WaterSourceUpdateDTO> {

    private Long id;
    private LocalDateTime updateDate  = LocalDateTime.now();
    private String descricao;
    private WaterSourceStatus oldStatus  = WaterSourceStatus.Potavel;
    private WaterSourceStatus status  = WaterSourceStatus.Potavel;
    private double latitude;
    private double longitude;
    private WaterSource waterSource;

    public WaterSourceUpdateDTO(WaterSourceUpdate waterSourceUpdate) {
        this.id = waterSourceUpdate.getId();
        this.descricao = waterSourceUpdate.getDescricao();
        this.oldStatus = waterSourceUpdate.getStatus();
        this.status = waterSourceUpdate.getStatus();
        this.latitude = waterSourceUpdate.getLatitude();
        this.longitude = waterSourceUpdate.getLongitude();
        this.waterSource = waterSourceUpdate.getWaterSource();
    }
}
