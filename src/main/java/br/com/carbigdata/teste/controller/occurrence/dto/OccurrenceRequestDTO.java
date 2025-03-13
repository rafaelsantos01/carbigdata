package br.com.carbigdata.teste.controller.occurrence.dto;

import br.com.carbigdata.teste.ENUM.SITUATION_INCIDENT;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class OccurrenceRequestDTO {

    private Timestamp dtaOcorrencia;

    private SITUATION_INCIDENT staOcorrencia;

    private Long customerId;

    private Long addressId;

}
