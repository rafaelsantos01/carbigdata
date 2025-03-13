package br.com.carbigdata.teste.service.customer;

import br.com.carbigdata.teste.controller.customer.dto.CustomerPaginatedResponseDTO;
import br.com.carbigdata.teste.controller.customer.dto.CustomerRequestDTO;
import br.com.carbigdata.teste.domain.customer.dto.CustomerDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ICustomerService {

    CustomerDTO createCustomer(@Valid @RequestBody CustomerRequestDTO customerDTO);

    CustomerDTO updateCustomer(@Valid @RequestBody CustomerRequestDTO customerDTO,@PathVariable Long id);

    void deleteCustomer(@PathVariable Long id);

    CustomerDTO getCustomer(@PathVariable Long id);

    List<CustomerPaginatedResponseDTO> getCustomers(int page, int size);
}
