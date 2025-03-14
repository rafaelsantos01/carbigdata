package br.com.carbigdata.teste.controller.occurrence.dto;

import br.com.carbigdata.teste.domain.occurrence.dto.OccurrenceDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OccurrencePaginateResponseDTO {

    private List<OccurrenceDTO> content;

    private int totalPages;
    private long totalElements;
}
