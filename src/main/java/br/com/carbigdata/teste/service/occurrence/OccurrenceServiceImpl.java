package br.com.carbigdata.teste.service.occurrence;

import br.com.carbigdata.teste.ENUM.SITUATION_INCIDENT;
import br.com.carbigdata.teste.controller.occurrence.dto.OccurrencePaginateResponseDTO;
import br.com.carbigdata.teste.controller.occurrence.dto.OccurrenceRequestDTO;
import br.com.carbigdata.teste.domain.address.Address;
import br.com.carbigdata.teste.domain.address.dto.AddressDTO;
import br.com.carbigdata.teste.domain.customer.Customer;
import br.com.carbigdata.teste.domain.customer.dto.CustomerDTO;
import br.com.carbigdata.teste.domain.occurrence.Occurrence;
import br.com.carbigdata.teste.domain.occurrence.dto.OccurrenceDTO;
import br.com.carbigdata.teste.repository.AddressRepository;
import br.com.carbigdata.teste.repository.CustomerRepository;
import br.com.carbigdata.teste.repository.OccurrenceRepository;
import br.com.carbigdata.teste.utils.UtilDocuments;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OccurrenceServiceImpl implements IOccurrenceService {

    private final OccurrenceRepository  occurrenceRepository;

    private final AddressRepository addressRepository;

    private final CustomerRepository customerRepository;

    private final UtilDocuments utilDocuments;

    @Override
    public OccurrenceDTO createOccurrence(OccurrenceRequestDTO request) {
        Address address = addressRepository.findById(request.getAddressId()).orElseThrow(() -> new Error("Address not found"));

        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow(() -> new Error("Customer not found"));

        Occurrence occurrence = new Occurrence();
        occurrence.setAddress(address);
        occurrence.setCustomer(customer);
        occurrence.setStaOcorrencia(request.getStaOcorrencia());
        occurrence.setDtaOcorrencia(request.getDtaOcorrencia());

        Occurrence occurrenceSaved = occurrenceRepository.saveAndFlush(occurrence);

        return createResponseOccurrence(occurrenceSaved, address, customer);
    }

    @Override
    public OccurrenceDTO updateOccurrence(Long id, OccurrenceRequestDTO request) {
        Occurrence occurrence = findByIdOccurrence(id);

        if(occurrence.getStaOcorrencia().equals(SITUATION_INCIDENT.FINALIZADA)){
            throw new Error("Ocorrência ja finalizada");
        }

        occurrence.setDtaOcorrencia(request.getDtaOcorrencia());
        occurrence.setStaOcorrencia(request.getStaOcorrencia());

        if(!request.getAddressId().equals(occurrence.getAddress().getCodEndereco())){
            Address address = addressRepository.findById(request.getAddressId()).orElseThrow(() -> new Error("Address not found"));
            occurrence.setAddress(address);
        }

        if(!request.getCustomerId().equals(occurrence.getCustomer().getCodCliente())){
            Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow(() -> new Error("Customer not found"));
            occurrence.setCustomer(customer);
        }

        Occurrence occurrenceSaved = occurrenceRepository.saveAndFlush(occurrence);

        return createResponseOccurrence(occurrenceSaved, occurrenceSaved.getAddress(), occurrenceSaved.getCustomer());
    }

    @Override
    public void deleteOccurrence(Long id) {
        Occurrence occurrence = findByIdOccurrence(id);
        occurrenceRepository.deleteById(occurrence.getCodOcorrencia());
    }

    @Override
    public OccurrenceDTO getOccurrence(Long id) {
        Occurrence occurrence = findByIdOccurrence(id);

        return createResponseOccurrence(occurrence, occurrence.getAddress(), occurrence.getCustomer());
    }

    @Override
    public List<OccurrencePaginateResponseDTO> getOccurrences(int page, int size) {
        List<OccurrencePaginateResponseDTO> responseDTOS = new ArrayList<>();
        OccurrencePaginateResponseDTO occurrencePaginateResponseDTO = new OccurrencePaginateResponseDTO();
        List<OccurrenceDTO> content = new ArrayList<>();

        Pageable pageable = PageRequest.of(page, size);
        Page<Occurrence> occurrenceList = occurrenceRepository.findAll(pageable);

        for(Occurrence occurrence : occurrenceList){
            OccurrenceDTO responseOccurrence = createResponseOccurrence(occurrence, occurrence.getAddress(), occurrence.getCustomer());
            content.add(responseOccurrence);
        }

        occurrencePaginateResponseDTO.setContent(content);
        occurrencePaginateResponseDTO.setTotalPages(occurrenceList.getTotalPages());
        occurrencePaginateResponseDTO.setTotalElements(occurrenceList.getTotalElements());
        responseDTOS.add(occurrencePaginateResponseDTO);
        return responseDTOS;
    }

    private Occurrence findByIdOccurrence(Long id) {
        return occurrenceRepository.findById(id).orElseThrow(() -> new Error("Occurrence not found"));
    }


    private OccurrenceDTO createResponseOccurrence(Occurrence occurrence, Address address, Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCodCliente(customer.getCodCliente());
        customerDTO.setNmeCliente(customer.getNmeCliente());
        customerDTO.setDtaNascimento(customer.getDtaNascimento());
        customerDTO.setNroCpf(utilDocuments.maskCpf(customer.getNroCpf()));
        customerDTO.setDtaCriacao(customer.getDtaCriacao());

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCodEndereco(address.getCodEndereco());
        addressDTO.setNmeCidade(address.getNmeCidade());
        addressDTO.setNmeEstado(address.getNmeEstado());
        addressDTO.setNroCep(address.getNroCep());
        addressDTO.setNmeBairro(address.getNmeBairro());
        addressDTO.setNmeLogradouro(address.getNmeLogradouro());


        OccurrenceDTO occurrenceDTO = new OccurrenceDTO();
        occurrenceDTO.setCodOcorrencia(occurrence.getCodOcorrencia());
        occurrenceDTO.setAddress(addressDTO);
        occurrenceDTO.setCustomer(customerDTO);
        occurrenceDTO.setStaOcorrencia(occurrence.getStaOcorrencia());
        occurrenceDTO.setDtaOcorrencia(occurrence.getDtaOcorrencia());

        return occurrenceDTO;
    }
}
