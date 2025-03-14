package br.com.carbigdata.teste.domain.address.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {

    @Schema(example = "1")
    private Long codEndereco;

    @Schema(example = "Rua das Flores")
    private String nmeLogradouro;

    @Schema(example = "Centro")
    private String nmeBairro;

    @Schema(example = "12345-678")
    private String nroCep;

    @Schema(example = "São Paulo")
    private String nmeCidade;

    @Schema(example = "São Paulo")
    private String nmeEstado;
}