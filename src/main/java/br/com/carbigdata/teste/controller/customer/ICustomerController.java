package br.com.carbigdata.teste.controller.customer;

import br.com.carbigdata.teste.controller.customer.dto.CustomerPaginatedResponseDTO;
import br.com.carbigdata.teste.controller.customer.dto.CustomerRequestDTO;
import br.com.carbigdata.teste.domain.customer.dto.CustomerDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Clientes", description = "Gerenciamento de clientes")
public interface ICustomerController {


    @Operation(
            summary = "Cria um novo cliente",
            description = "Registra um novo cliente no sistema e retorna os detalhes do cliente criado."
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CustomerDTO createCustomer(@Valid @RequestBody CustomerRequestDTO customerDTO);


    @Operation(
            summary = "Atualiza um cliente",
            description = "Atualiza os dados de um cliente existente com base no ID fornecido."
    )
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    CustomerDTO updateCustomer(@Valid @RequestBody CustomerRequestDTO customerDTO, @PathVariable Long id);


    @Operation(
            summary = "Exclui um cliente",
            description = "Remove um cliente do sistema com base no ID fornecido."
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCustomer(@PathVariable Long id);


    @Operation(
            summary = "Busca um cliente pelo ID",
            description = "Retorna os detalhes de um cliente com base no ID fornecido."
    )
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    CustomerDTO getCustomer(@PathVariable Long id);


    @Operation(
            summary = "Lista clientes paginados",
            description = "Retorna uma lista de clientes paginados com parâmetros opcionais de página e tamanho."
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<CustomerPaginatedResponseDTO> getCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    );
}
