package br.com.carbigdata.teste.service.address;

import br.com.carbigdata.teste.controller.address.dto.AddressPaginatedResponseDTO;
import br.com.carbigdata.teste.controller.address.dto.CreateAddressRequestDTO;
import br.com.carbigdata.teste.domain.address.Address;
import br.com.carbigdata.teste.domain.address.AddressDTO;
import br.com.carbigdata.teste.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements IAddressService {

    private final AddressRepository addressRepository;

    @Override
    public AddressDTO createAddress(CreateAddressRequestDTO addressRequestDTO) {
        Address address = new Address();
        address.setNmeBairro(addressRequestDTO.getNmeBairro());
        address.setNmeCidade(addressRequestDTO.getNmeCidade());
        address.setNmeEstado(addressRequestDTO.getNmeEstado());
        address.setNmeLogradouro(addressRequestDTO.getNmeLogradouro());
        address.setNroCep(addressRequestDTO.getNroCep());

        Address addressSaved = addressRepository.saveAndFlush(address);
        return createResponse(addressSaved);
    }

    @Override
    public AddressDTO updateAddress(CreateAddressRequestDTO addressRequestDTO, Long id) {
        Address address = findByAddressId(id);

        address.setNmeBairro(addressRequestDTO.getNmeBairro());
        address.setNmeCidade(addressRequestDTO.getNmeCidade());
        address.setNmeEstado(addressRequestDTO.getNmeEstado());
        address.setNmeLogradouro(addressRequestDTO.getNmeLogradouro());
        address.setNroCep(addressRequestDTO.getNroCep());

        Address addressSaved = addressRepository.saveAndFlush(address);
        return createResponse(addressSaved);
    }

    @Override
    public void deleteAddress(Long id) {
        Address address = findByAddressId(id);
        addressRepository.delete(address);
    }

    @Override
    public AddressDTO getAddress(Long id) {
        Address address = findByAddressId(id);

        return  createResponse(address);
    }

    @Override
    public List<AddressPaginatedResponseDTO> getAllAddress(int size, int page) {
        List<AddressPaginatedResponseDTO> listResponse = new ArrayList<>();
        List<AddressDTO> content = new ArrayList<>();
        AddressPaginatedResponseDTO addressPaginatedResponseDTO = new AddressPaginatedResponseDTO();

        Pageable pageable = PageRequest.of(page, size);

        Page<Address> addressList = addressRepository.findAll(pageable);

        for(Address address : addressList){
            AddressDTO response = createResponse(address);
            content.add(response);
        }

        addressPaginatedResponseDTO.setTotalElements(addressList.getTotalElements());
        addressPaginatedResponseDTO.setTotalPages(addressList.getTotalPages());
        addressPaginatedResponseDTO.setContent(content);
        listResponse.add(addressPaginatedResponseDTO);

        return listResponse;
    }


    private Address findByAddressId(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new Error("Address not found"));
    }


    private AddressDTO createResponse(Address address) {
        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setNmeBairro(address.getNmeBairro());
        addressDTO.setNmeCidade(address.getNmeCidade());
        addressDTO.setNmeEstado(address.getNmeEstado());
        addressDTO.setNmeLogradouro(address.getNmeLogradouro());
        addressDTO.setNroCep(address.getNroCep());
        addressDTO.setCodEndereco(address.getCodEndereco());

        return addressDTO;
    }
}
