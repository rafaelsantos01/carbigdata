package br.com.carbigdata.teste.controller.occurrence.dto;

import br.com.carbigdata.teste.ENUM.SITUATION_INCIDENT;
import br.com.carbigdata.teste.domain.occurrence.dto.PhotoOccurrenceDTO;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class CreateFileOccurrenceDTO {

    private Long codOcorrencia;

    private Timestamp dtaOcorrencia;

    private SITUATION_INCIDENT staOcorrencia;

    private List<PhotoOccurrenceDTO> photos;
}
