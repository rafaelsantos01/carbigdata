package br.com.carbigdata.teste.domain.occurrence.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;


@Getter
@Setter
public class PhotoOccurrenceDTO {
    private Long codFotoOcorrencia;

    private Timestamp dtaCriacao;

    private String dscPathBucket;

    private String dscHash;

    private OccurrenceDTO occurrence;
}