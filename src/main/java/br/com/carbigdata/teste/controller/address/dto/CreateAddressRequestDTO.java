package br.com.carbigdata.teste.controller.address.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAddressRequestDTO {

    @NotBlank
    @Schema( example = "Rua das Flores")
    private String nmeLogradouro;

    @NotBlank
    @Schema(example = "Centro")
    private String nmeBairro;

    @NotBlank
    @Schema(example = "12345-678")
    private String nroCep;

    @NotBlank
    @Schema(example = "São Paulo")
    private String nmeCidade;

    @NotBlank
    @Schema(example = "São Paulo")
    private String nmeEstado;
}
