package br.com.carbigdata.teste.domain.occurrence.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;


@Getter
@Setter
public class PhotoOccurrenceDTO {

    @Schema(example = "1")
    private Long codFotoOcorrencia;

    @Schema(example = "2025-03-13T14:30:00.000Z")
    private Timestamp dtaCriacao;

    @Schema(example = "http://localhost:9000/ocorrencia/1/1.jpg")
    private String dscPathBucket;

    @Schema(example = "1")
    private String dscHash;

    private OccurrenceDTO occurrence;
}