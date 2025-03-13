package br.com.carbigdata.teste.controller.customer;

import br.com.carbigdata.teste.controller.customer.dto.CustomerPaginatedResponseDTO;
import br.com.carbigdata.teste.controller.customer.dto.CustomerRequestDTO;
import br.com.carbigdata.teste.domain.customer.dto.CustomerDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ICustomerController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CustomerDTO createCustomer(@Valid @RequestBody CustomerRequestDTO customerDTO);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    CustomerDTO updateCustomer(@Valid @RequestBody CustomerRequestDTO customerDTO,@PathVariable Long id);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCustomer(@PathVariable Long id);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    CustomerDTO getCustomer(@PathVariable Long id);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<CustomerPaginatedResponseDTO> getCustomers(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "5") int size);
}
