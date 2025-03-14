package br.com.carbigdata.teste.domain.occurrence.dto;

import br.com.carbigdata.teste.ENUM.SITUATION_INCIDENT;
import br.com.carbigdata.teste.domain.address.dto.AddressDTO;
import br.com.carbigdata.teste.domain.customer.dto.CustomerDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OccurrenceDTO {

    @Schema(example = "1")
    private Long codOcorrencia;

    @Schema(example = "2025-03-13T14:30:00.000Z")
    private Timestamp dtaOcorrencia;

    @Schema(example = "ATIVA")
    private SITUATION_INCIDENT staOcorrencia;

    private CustomerDTO customer;

    private AddressDTO address;

    private List<PhotoOccurrenceDTO> photoOccurrence;
}