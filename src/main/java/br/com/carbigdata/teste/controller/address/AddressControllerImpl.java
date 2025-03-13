package br.com.carbigdata.teste.controller.address;

import br.com.carbigdata.teste.controller.address.dto.AddressPaginatedResponseDTO;
import br.com.carbigdata.teste.controller.address.dto.CreateAddressRequestDTO;
import br.com.carbigdata.teste.domain.address.AddressDTO;
import br.com.carbigdata.teste.service.address.IAddressService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
@AllArgsConstructor
public class AddressControllerImpl implements IAddressController{

    private final IAddressService addressService;

    @Override
    public AddressDTO createAddress(CreateAddressRequestDTO addressRequestDTO) {
        return addressService.createAddress(addressRequestDTO);
    }

    @Override
    public AddressDTO updateAddress(CreateAddressRequestDTO addressRequestDTO, Long id) {
        return addressService.updateAddress(addressRequestDTO, id);
    }

    @Override
    public void deleteAddress(Long id) {
        addressService.deleteAddress(id);
    }

    @Override
    public AddressDTO getAddress(Long id) {
        return addressService.getAddress(id);
    }

    @Override
    public List<AddressPaginatedResponseDTO> getAllAddress(int size, int page) {
        return addressService.getAllAddress(size, page);
    }
}
