package br.com.carbigdata.teste.controller.address;

import br.com.carbigdata.teste.controller.address.dto.AddressPaginatedResponseDTO;
import br.com.carbigdata.teste.controller.address.dto.CreateAddressRequestDTO;
import br.com.carbigdata.teste.domain.address.AddressDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IAddressController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    AddressDTO createAddress(@RequestBody @Valid CreateAddressRequestDTO addressRequestDTO);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    AddressDTO updateAddress(@RequestBody @Valid CreateAddressRequestDTO addressRequestDTO, @PathVariable Long id);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteAddress(@PathVariable  Long id);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    AddressDTO getAddress(@PathVariable Long id);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<AddressPaginatedResponseDTO> getAllAddress(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "5") int size);
}
