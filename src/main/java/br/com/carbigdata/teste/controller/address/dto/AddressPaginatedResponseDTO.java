package br.com.carbigdata.teste.controller.address.dto;

import br.com.carbigdata.teste.domain.address.dto.AddressDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddressPaginatedResponseDTO {

    private List<AddressDTO> content;

    @Schema(example = "2")
    private int totalPages;

    @Schema(example = "10")
    private long totalElements;
}
