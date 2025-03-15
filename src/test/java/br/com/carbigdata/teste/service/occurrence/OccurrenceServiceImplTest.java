package br.com.carbigdata.teste.service.occurrence;

import br.com.carbigdata.teste.ENUM.SITUATION_INCIDENT;
import br.com.carbigdata.teste.controller.address.dto.CreateAddressRequestDTO;
import br.com.carbigdata.teste.controller.occurrence.dto.OccurrencePaginateResponseDTO;
import br.com.carbigdata.teste.controller.occurrence.dto.OccurrenceRequestDTO;
import br.com.carbigdata.teste.controller.occurrence.dto.UpdateOccurrenceRequestDTO;
import br.com.carbigdata.teste.domain.address.Address;
import br.com.carbigdata.teste.domain.address.dto.AddressDTO;
import br.com.carbigdata.teste.domain.customer.Customer;
import br.com.carbigdata.teste.domain.occurrence.Occurrence;
import br.com.carbigdata.teste.domain.occurrence.PhotoOccurrence;
import br.com.carbigdata.teste.domain.occurrence.dto.OccurrenceDTO;
import br.com.carbigdata.teste.repository.AddressRepository;
import br.com.carbigdata.teste.repository.CustomerRepository;
import br.com.carbigdata.teste.repository.OccurrenceRepository;
import br.com.carbigdata.teste.service.address.IAddressService;
import br.com.carbigdata.teste.service.customer.ICustomerService;
import br.com.carbigdata.teste.utils.UtilDocuments;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OccurrenceServiceImplTest {

    @Mock
    private OccurrenceRepository occurrenceRepository;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private UtilDocuments utilDocuments;
    @Mock
    private ICustomerService customerService;
    @Mock
    private IAddressService addressService;
    @Mock
    private IPhotoOccurrenceService photoOccurrenceService;

    @InjectMocks
    private OccurrenceServiceImpl occurrenceService;

    @Test
    @DisplayName("Create Occurrence - Success")
    void testCreateOccurrence() {
        Long customerId = 1L;
        Long addressId = 1L;
        OccurrenceRequestDTO request = request();

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer()));
        when(addressRepository.findById(addressId)).thenReturn(Optional.of(createAddress()));
        when(occurrenceRepository.saveAndFlush(any())).thenReturn(occurrence());

        OccurrenceDTO occurrence = occurrenceService.createOccurrence(request, customerId, addressId);

        assertNotNull(occurrence);
        assertEquals(SITUATION_INCIDENT.ATIVA, occurrence.getStaOcorrencia());
    }

    @Test
    @DisplayName("Update with new address and customer Occurrence - Success")
    void testUpdateOccurrenceNewCustomerAndNewAddress() {
        Long occurrenceId = 1L;
        UpdateOccurrenceRequestDTO request = updateRequest();

        when(occurrenceRepository.findById(occurrenceId)).thenReturn(Optional.of(occurrence()));
        when(customerRepository.findById(request.getCustomerId())).thenReturn(Optional.of(customer()));
        when(addressRepository.findById(request.getAddressId())).thenReturn(Optional.of(createAddress()));
        when(occurrenceRepository.saveAndFlush(any())).thenReturn(occurrence());

        OccurrenceDTO occurrenceDTO = occurrenceService.updateOccurrence(occurrenceId, request);
        assertNotNull(occurrenceDTO);
        assertEquals(SITUATION_INCIDENT.ATIVA, occurrenceDTO.getStaOcorrencia());
    }

    @Test
    @DisplayName("Update  Occurrence - Success")
    void testUpdateOccurrence() {
        Occurrence occurrence = occurrence();
        Long occurrenceId = 1L;
        UpdateOccurrenceRequestDTO request = updateRequest();
        request.setCustomerId(occurrence.getCustomer().getCodCliente());
        request.setAddressId(occurrence.getAddress().getCodEndereco());

        when(occurrenceRepository.findById(occurrenceId)).thenReturn(Optional.of(occurrence));
        when(occurrenceRepository.saveAndFlush(any())).thenReturn(occurrence());

        OccurrenceDTO occurrenceDTO = occurrenceService.updateOccurrence(occurrenceId, request);
        assertNotNull(occurrenceDTO);
        assertEquals(SITUATION_INCIDENT.ATIVA, occurrenceDTO.getStaOcorrencia());
    }

    @Test
    @DisplayName("Delete Occurrence none photo - Success")
    void testDeleteOccurrence() {
        List<PhotoOccurrence> photoOccurrences = new ArrayList<>();

        Long occurrenceId = 1L;
        Occurrence occurrence = occurrence();
        occurrence.setPhotoOccurrences(photoOccurrences);

        when(occurrenceRepository.findById(occurrenceId)).thenReturn(Optional.of(occurrence));

        occurrenceService.deleteOccurrence(occurrenceId);
    }

    @Test
    @DisplayName("Delete Occurrence  - Success")
    void testDeleteOccurrenceWithPhoto() {
        PhotoOccurrence photoOccurrence = new PhotoOccurrence();
        photoOccurrence.setCodFotoOcorrencia(1L);

        List<PhotoOccurrence> photoOccurrences = new ArrayList<>();
        photoOccurrences.add(photoOccurrence);

        Long occurrenceId = 1L;
        Occurrence occurrence = occurrence();
        occurrence.setPhotoOccurrences(photoOccurrences);

        when(occurrenceRepository.findById(occurrenceId)).thenReturn(Optional.of(occurrence));

        occurrenceService.deleteOccurrence(occurrenceId);
    }

    @Test
    @DisplayName("Get Occurrence - Success")
    void testGetOccurrence() {
        Long occurrenceId = 1L;
        Occurrence occurrence = occurrence();
        occurrence.setPhotoOccurrences(new ArrayList<>());

        when(occurrenceRepository.findById(occurrenceId)).thenReturn(Optional.of(occurrence));

        OccurrenceDTO occurrenceDto = occurrenceService.getOccurrence(occurrenceId);
        assertNotNull(occurrenceDto);
    }

    @Test
    @DisplayName("Get Occurrence - Error")
    void testGetOccurrencesDetails() {
        Occurrence occurrence1 = occurrence();
        Occurrence occurrence2 = occurrence();

        List<Occurrence> occurrenceList = List.of(occurrence1, occurrence2);

        Pageable pageable = PageRequest.of(0, 2);

        Page<Occurrence> mockPage = new PageImpl<>(occurrenceList, pageable, occurrenceList.size());

        when(occurrenceRepository.findAll(any(Pageable.class))).thenReturn(mockPage);

        List<OccurrencePaginateResponseDTO> occurrencesDetails = occurrenceService.getOccurrencesDetails(0, 2);
        assertNotNull(occurrencesDetails);
        assertEquals(1, occurrencesDetails.size());
    }

    @Test
    @DisplayName("Finalize Occurrence - Success")
    void finalizeOccurrence() {
        Long occurrenceId = 1L;

        when(occurrenceRepository.findById(occurrenceId)).thenReturn(Optional.of(occurrence()));

        occurrenceService.finalizeOccurrence(occurrenceId);
    }

    @Test
    @DisplayName("Error finalizing the occurrence already completed- Error")
    void finalizeOccurrenceError() {
        Long occurrenceId = 1L;

        Occurrence occurrence = occurrence();
        occurrence.setStaOcorrencia(SITUATION_INCIDENT.FINALIZADA);

        when(occurrenceRepository.findById(occurrenceId)).thenReturn(Optional.of(occurrence));

        Error exception = assertThrows(Error.class, () ->  occurrenceService.finalizeOccurrence(occurrenceId));
        assertEquals("Ocorrência já finalizada", exception.getMessage());
    }

    @Test
    @DisplayName("Create Occurrence Complete - Success")
    void createOccurrenceComplete() throws JsonProcessingException {
        String json = "{\"nro_cpf\": \"123.456.789-00\", \"nme_cliente\": \"João da Silva\", \"dtaOcorrencia\": \"2024-03-14T10:30:00\", \"nmeLogradouro\": \"Rua Exemplo\", \"nmeBairro\": \"Centro\", \"nroCep\": \"12345-678\", \"nmeCidade\": \"São Paulo\", \"nmeEstado\": \"SP\"}";

        when(utilDocuments.clearDocument(anyString())).thenReturn("12345678900");
        when(customerRepository.findByNroCpf(anyString())).thenReturn(Optional.of(customer()));
        when(addressService.createAddress(any(CreateAddressRequestDTO.class))).thenReturn(addressDTO());
        when(occurrenceRepository.saveAndFlush(any())).thenReturn(occurrence());


        occurrenceService.createOccurrenceComplete(new ArrayList<>(),json);
    }

    private OccurrenceRequestDTO request() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        OccurrenceRequestDTO request = new OccurrenceRequestDTO();
        request.setDtaOcorrencia(timestamp);
        request.setStaOcorrencia(SITUATION_INCIDENT.FINALIZADA);
        return request;
    }


    private Occurrence occurrence() {
        Occurrence occurrence = new Occurrence();
        occurrence.setCodOcorrencia(1L);
        occurrence.setDtaOcorrencia(new Timestamp(System.currentTimeMillis()));
        occurrence.setStaOcorrencia(SITUATION_INCIDENT.ATIVA);
        occurrence.setAddress(createAddress());
        occurrence.setCustomer(customer());

        return occurrence;
    }

    private Address createAddress() {
        Address address = new Address();
        address.setCodEndereco(1L);
        address.setNmeBairro("Centro");
        address.setNmeCidade("São Paulo");
        return address;
    }

    private Customer customer() {
        Customer customer = new Customer();
        customer.setCodCliente(1L);
        customer.setNmeCliente("João");
        customer.setNroCpf("12345678901");
        customer.setDtaCriacao(new Timestamp(System.currentTimeMillis()));

        return customer;
    }

    private UpdateOccurrenceRequestDTO updateRequest() {
        UpdateOccurrenceRequestDTO request = new UpdateOccurrenceRequestDTO();
        request.setDtaOcorrencia(new Timestamp(System.currentTimeMillis()));
        request.setStaOcorrencia(SITUATION_INCIDENT.ATIVA);
        request.setCustomerId(5L);
        request.setAddressId(5L);
        return request;
    }

    private AddressDTO addressDTO(){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setNmeLogradouro("Rua Exemplo");
        addressDTO.setNmeBairro("Centro");
        addressDTO.setNroCep("12345-678");
        addressDTO.setNmeCidade("São Paulo");
        addressDTO.setNmeEstado("SP");
        return addressDTO;
    }
}