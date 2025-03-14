package br.com.carbigdata.teste.domain.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
public class CustomerDTO {

    @Schema(example = "1")
    private Long codCliente;

    @Schema(example = "Fulano de Tal")
    private String nmeCliente;

    @Schema(example = "1990-01-01")
    private Date dtaNascimento;

    @Schema(example = "12345678901")
    private String nroCpf;

    @Schema(example = "2025-03-13T14:30:00.000Z")
    private Timestamp dtaCriacao;

}