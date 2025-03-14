package br.com.carbigdata.teste.controller.occurrence.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.sql.Timestamp;

@Getter
@Setter
public class OccurrenceCompleteRequestDTO {

    @CPF
    private String nro_cpf;

    @NotBlank
    private String nme_cliente;

    private Timestamp dtaOcorrencia;

    @NotBlank
    private String nmeLogradouro;

    @NotBlank
    private String nmeBairro;

    @NotBlank
    private String nroCep;

    @NotBlank
    private String nmeCidade;

    @NotBlank
    private String nmeEstado;

}
