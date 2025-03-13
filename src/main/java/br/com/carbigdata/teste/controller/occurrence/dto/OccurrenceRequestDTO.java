package br.com.carbigdata.teste.controller.occurrence.dto;

import br.com.carbigdata.teste.ENUM.SITUATION_INCIDENT;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class OccurrenceRequestDTO {

    @NotNull
    private Timestamp dtaOcorrencia;

    @NotNull
    private SITUATION_INCIDENT staOcorrencia;

    @NotNull
    private Long customerId;

    @NotNull
    private Long addressId;

}
