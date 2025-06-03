package com.fiap.globalsolution1.model;

import com.fiap.globalsolution1.model.enums.WaterSourceStatus;
import com.fiap.globalsolution1.model.enums.WaterSourceType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class WaterSource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode ser vazio")
    private String nome;

    @NotBlank(message = "A descrição não pode ser vazia")
    private String descricao;

    @NotBlank(message = "A localização não pode ser vazio")
    private String localizacao;

    @NotNull(message = "A latitude não pode ser vazia")
    private double latitude;

    @NotNull(message = "A longitude não pode ser vazia")
    private double longitude;

    @NotNull(message = "O tipo não pode ser vazio")
    private WaterSourceType type;

    @NotNull(message = "A latitude não pode ser vazia")
    private int createdById;

    @NotNull(message = "A data não pode ser vazia")
    private Date lastInspected;

    @NotNull(message = "Esta ativo não pode ser vazio")
    private boolean isActive = true;

    @NotNull(message = "A data de criação não pode ser vazia")
    private LocalDateTime createdAt  = LocalDateTime.now();

    @NotNull(message = "A data de atualização não pode ser vazia")
    private LocalDateTime updatedAt  = LocalDateTime.now();

    @NotNull(message = "O status não pode ser vazio")
    private WaterSourceStatus status  = WaterSourceStatus.Potavel;

    @OneToMany(mappedBy = "waterSource", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<WaterSourceUpdate> waterSourceUpdates;
}
