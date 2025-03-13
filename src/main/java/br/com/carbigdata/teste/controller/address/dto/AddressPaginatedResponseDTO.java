package br.com.carbigdata.teste.controller.address.dto;

import br.com.carbigdata.teste.domain.address.dto.AddressDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddressPaginatedResponseDTO {

    private List<AddressDTO> content;

    private int totalPages;
    private long totalElements;
}
