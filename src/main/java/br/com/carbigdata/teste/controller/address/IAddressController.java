package br.com.carbigdata.teste.controller.address;

import br.com.carbigdata.teste.controller.address.dto.AddressPaginatedResponseDTO;
import br.com.carbigdata.teste.controller.address.dto.CreateAddressRequestDTO;
import br.com.carbigdata.teste.domain.address.dto.AddressDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Endereços", description = "Gerenciamento de endereços")
public interface IAddressController {

    @Operation(
            summary = "Cria um novo endereço",
            description = "Registra um novo endereço no sistema e retorna os detalhes do endereço criado."
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    AddressDTO createAddress(@RequestBody @Valid CreateAddressRequestDTO addressRequestDTO);


    @Operation(
            summary = "Atualiza um endereço",
            description = "Atualiza os dados de um endereço existente com base no ID fornecido."
    )
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    AddressDTO updateAddress(@RequestBody @Valid CreateAddressRequestDTO addressRequestDTO, @PathVariable Long id);


    @Operation(
            summary = "Exclui um endereço",
            description = "Remove um endereço do sistema com base no ID fornecido."
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteAddress(@PathVariable  Long id);


    @Operation(
            summary = "Busca um endereço pelo ID",
            description = "Retorna os detalhes de um endereço com base no ID fornecido."
    )
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    AddressDTO getAddress(@PathVariable Long id);


    @Operation(
            summary = "Lista endereços paginados",
            description = "Retorna uma lista de endereços paginados com parâmetros opcionais de página e tamanho."
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<AddressPaginatedResponseDTO> getAllAddress(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "5") int size);
}
