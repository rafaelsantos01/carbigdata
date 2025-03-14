package br.com.carbigdata.teste.controller.customer.dto;

import br.com.carbigdata.teste.domain.customer.dto.CustomerDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerPaginatedResponseDTO {
    private List<CustomerDTO> contet;

    private int totalPages;
    private long totalElements;
}
