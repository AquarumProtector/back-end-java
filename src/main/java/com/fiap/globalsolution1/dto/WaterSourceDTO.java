package com.fiap.globalsolution1.dto;

import com.fiap.globalsolution1.model.WaterSource;
import com.fiap.globalsolution1.model.WaterSourceUpdate;
import com.fiap.globalsolution1.model.enums.WaterSourceStatus;
import com.fiap.globalsolution1.model.enums.WaterSourceType;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class WaterSourceDTO extends RepresentationModel<WaterSourceDTO> {

    private Long id;
    private String nome;
    private String descricao;
    private String localizacao;
    private double latitude;
    private double longitude;
    private WaterSourceType type;
    private int createdById;
    private Date lastInspected;
    private boolean isActive = true;
    private LocalDateTime createdAt  = LocalDateTime.now();
    private LocalDateTime updatedAt  = LocalDateTime.now();
    private WaterSourceStatus status  = WaterSourceStatus.Potavel;
    private List<WaterSourceUpdate> waterSourceUpdates;

    public WaterSourceDTO(WaterSource waterSource) {
        this.id = waterSource.getId();
        this.nome = waterSource.getNome();
        this.descricao = waterSource.getDescricao();
        this.localizacao = waterSource.getLocalizacao();
        this.latitude = waterSource.getLatitude();
        this.longitude = waterSource.getLongitude();
        this.type = waterSource.getType();
        this.createdById = waterSource.getCreatedById();
        this.lastInspected = waterSource.getLastInspected();
        this.isActive = waterSource.isActive();
        this.createdAt = waterSource.getCreatedAt();
        this.updatedAt = waterSource.getUpdatedAt();
        this.status = waterSource.getStatus();
        this.waterSourceUpdates = waterSource.getWaterSourceUpdates();
    }
}
