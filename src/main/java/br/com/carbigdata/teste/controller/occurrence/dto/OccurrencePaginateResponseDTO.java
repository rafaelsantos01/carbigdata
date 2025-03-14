package br.com.carbigdata.teste.controller.occurrence.dto;

import br.com.carbigdata.teste.domain.occurrence.dto.OccurrenceDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OccurrencePaginateResponseDTO {

    private List<OccurrenceDTO> content;

    @Schema(example = "1")
    private int totalPages;

    @Schema(example = "2")
    private long totalElements;
}
