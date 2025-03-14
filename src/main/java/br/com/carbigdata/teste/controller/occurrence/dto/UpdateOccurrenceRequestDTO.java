package br.com.carbigdata.teste.controller.occurrence.dto;

import br.com.carbigdata.teste.ENUM.SITUATION_INCIDENT;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class UpdateOccurrenceRequestDTO {

    @NotNull
    @Schema(example = "2025-03-13T14:30:00.000Z")
    private Timestamp dtaOcorrencia;

    @NotNull
    @Schema(example = "ATIVA")
    private SITUATION_INCIDENT staOcorrencia;

    @NotNull
    @Schema(example = "1")
    private Long addressId;

    @NotNull
    @Schema(example = "2")
    private Long customerId;
}
