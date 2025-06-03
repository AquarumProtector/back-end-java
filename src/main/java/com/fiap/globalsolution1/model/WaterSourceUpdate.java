package com.fiap.globalsolution1.model;

import com.fiap.globalsolution1.model.enums.WaterSourceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class WaterSourceUpdate
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull(message = "A data de atualização não pode ser vazia")
    private LocalDateTime UpdateDate  = LocalDateTime.now();

    @NotBlank(message = "A descrição não pode ser vazia")
    private String Descricao;

    @NotBlank(message = "O status antigo não pode ser vazio")
    private WaterSourceStatus OldStatus  = WaterSourceStatus.Potavel;

    @NotBlank(message = "O status não pode ser vazio")
    private WaterSourceStatus Status  = WaterSourceStatus.Potavel;

    @NotBlank(message = "A latitude não pode ser vazia")
    private double Latitude;

    @NotBlank(message = "A longitude não pode ser vazia")
    private double Longitude;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "watersource_id")
    private WaterSource waterSource;
}
