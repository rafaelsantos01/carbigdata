package br.com.carbigdata.teste.common.exceptions.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ErrorDTO {

    @Schema(example = "Erro ao processar a requisição")
    private String message;

    @Schema(example = "400")
    private int status;

    @Schema(example = "2021-07-01T10:00:00")
    private String timestamp;


    public ErrorDTO(String message, int status) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
