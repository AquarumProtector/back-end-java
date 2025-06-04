package com.fiap.globalsolution1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fiap.globalsolution1.model.enums.WaterSourceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tb_atualizacao_fonte_agua")
public class WaterSourceUpdate
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A data de atualização não pode ser vazia")
    private LocalDateTime updateDate  = LocalDateTime.now();

    @NotBlank(message = "A descrição não pode ser vazia")
    private String descricao;

    @NotNull(message = "O status antigo não pode ser vazio")
    private WaterSourceStatus oldStatus  = WaterSourceStatus.Potavel;

    @NotNull(message = "O status não pode ser vazio")
    private WaterSourceStatus status  = WaterSourceStatus.Potavel;

    @NotNull(message = "A latitude não pode ser vazia")
    private double latitude;

    @NotNull(message = "A longitude não pode ser vazia")
    private double longitude;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "watersource_id")
    @JsonBackReference
    private WaterSource waterSource;
}
