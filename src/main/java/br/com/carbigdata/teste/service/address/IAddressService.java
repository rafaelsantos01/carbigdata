package br.com.carbigdata.teste.service.address;

import br.com.carbigdata.teste.controller.address.dto.AddressPaginatedResponseDTO;
import br.com.carbigdata.teste.controller.address.dto.CreateAddressRequestDTO;
import br.com.carbigdata.teste.domain.address.AddressDTO;

import java.util.List;

public interface IAddressService {

    AddressDTO createAddress(CreateAddressRequestDTO addressRequestDTO);

    AddressDTO updateAddress(CreateAddressRequestDTO addressRequestDTO, Long id);

    void deleteAddress(  Long id);

    AddressDTO getAddress( Long id);

    List<AddressPaginatedResponseDTO> getAllAddress(int size, int page);
}
