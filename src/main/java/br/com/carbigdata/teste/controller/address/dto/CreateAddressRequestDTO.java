package br.com.carbigdata.teste.controller.address.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAddressRequestDTO {

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
