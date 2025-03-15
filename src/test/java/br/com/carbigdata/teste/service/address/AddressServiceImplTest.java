package br.com.carbigdata.teste.service.address;

import br.com.carbigdata.teste.controller.address.dto.AddressPaginatedResponseDTO;
import br.com.carbigdata.teste.controller.address.dto.CreateAddressRequestDTO;
import br.com.carbigdata.teste.domain.address.Address;
import br.com.carbigdata.teste.domain.address.dto.AddressDTO;
import br.com.carbigdata.teste.domain.occurrence.Occurrence;
import br.com.carbigdata.teste.repository.AddressRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImpl addressService;

    @Test
    @DisplayName("Create Address - Success")
    void testCreateAddressSuccess() {
        CreateAddressRequestDTO requestDTO = createAddressRequestDTO();
        Address savedAddress = createAddress();

        when(addressRepository.saveAndFlush(any(Address.class))).thenReturn(savedAddress);

        AddressDTO result = addressService.createAddress(requestDTO);

        assertNotNull(result);
        assertEquals("Centro", result.getNmeBairro());
        assertEquals("São Paulo", result.getNmeCidade());
    }

    @Test
    @DisplayName("Update Address - Success")
    void testUpdateAddressSuccess() {
        Long id = 1L;
        CreateAddressRequestDTO requestDTO = createAddressRequestDTO();
        Address existingAddress = createAddress();

        when(addressRepository.findById(id)).thenReturn(Optional.of(existingAddress));
        when(addressRepository.saveAndFlush(any(Address.class))).thenReturn(existingAddress);

        AddressDTO result = addressService.updateAddress(requestDTO, id);

        assertNotNull(result);
        assertEquals("São Paulo", result.getNmeCidade());
    }

    @Test
    @DisplayName("Delete Address - Success")
    void testDeleteAddressSuccess() {
        Long id = 1L;
        Address existingAddress = createAddress();

        when(addressRepository.findById(id)).thenReturn(Optional.of(existingAddress));
        doNothing().when(addressRepository).delete(existingAddress);

        assertDoesNotThrow(() -> addressService.deleteAddress(id));

        verify(addressRepository, times(1)).delete(existingAddress);
    }

    @Test
    @DisplayName("Not Delete Address with occurrence - Success")
    void testDeleteAddressError() {
        Occurrence occurrence = new Occurrence();
        List<Occurrence> occurrenceList = new ArrayList<>();
        occurrenceList.add(occurrence);
        Long id = 1L;
        Address existingAddress = createAddress();
        existingAddress.setOccurrenceList(occurrenceList);

        when(addressRepository.findById(id)).thenReturn(Optional.of(existingAddress));

        Error exception = assertThrows(Error.class, () ->  addressService.deleteAddress(id));
        assertEquals("O Endereço não pode ser deletado pois está associado a uma ocorrência", exception.getMessage());
    }

    @Test
    @DisplayName("Get Address - Success")
    void testGetAddressSuccess() {
        Long id = 1L;
        Address address =createAddress();

        when(addressRepository.findById(id)).thenReturn(Optional.of(address));

        AddressDTO result = addressService.getAddress(id);

        assertNotNull(result);
        assertEquals("Centro", result.getNmeBairro());
        assertEquals("São Paulo", result.getNmeCidade());
    }

    @Test
    @DisplayName("Get All Address - Success")
    void testGetAllAddressSuccess() {
        int page = 0, size = 2;
        Address address1 = createAddress();
        Address address2 = createAddress();
        Page<Address> pageResult = new PageImpl<>(List.of(address1, address2));

        when(addressRepository.findAll(any(Pageable.class))).thenReturn(pageResult);

        List<AddressPaginatedResponseDTO> result = addressService.getAllAddress(size, page);

        assertNotNull(result);
        assertEquals(2, result.get(0).getContent().size());
        assertEquals("Centro", result.get(0).getContent().get(0).getNmeBairro());
    }

    private CreateAddressRequestDTO createAddressRequestDTO() {
        CreateAddressRequestDTO requestDTO = new CreateAddressRequestDTO();
        requestDTO.setNmeBairro("Centro");
        requestDTO.setNmeCidade("São Paulo");
        requestDTO.setNmeEstado("SP");
        requestDTO.setNmeLogradouro("Rua A");
        requestDTO.setNroCep("01000-000");


        return requestDTO;
    }


    private Address createAddress() {
        Address address = new Address();
        address.setNmeBairro("Centro");
        address.setNmeCidade("São Paulo");
        address.setNmeEstado("SP");
        address.setNmeLogradouro("Rua A");
        address.setNroCep("01000-000");
        address.setCodEndereco(1L);
        address.setOccurrenceList(new ArrayList<>());

        return address;
    }
}