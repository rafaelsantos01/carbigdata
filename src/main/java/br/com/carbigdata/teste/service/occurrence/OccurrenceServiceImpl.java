package br.com.carbigdata.teste.service.occurrence;

import br.com.carbigdata.teste.ENUM.SITUATION_INCIDENT;
import br.com.carbigdata.teste.controller.address.dto.CreateAddressRequestDTO;
import br.com.carbigdata.teste.controller.customer.dto.CustomerRequestDTO;
import br.com.carbigdata.teste.controller.occurrence.dto.OccurrenceCompleteRequestDTO;
import br.com.carbigdata.teste.controller.occurrence.dto.OccurrencePaginateResponseDTO;
import br.com.carbigdata.teste.controller.occurrence.dto.OccurrenceRequestDTO;
import br.com.carbigdata.teste.controller.occurrence.dto.UpdateOccurrenceRequestDTO;
import br.com.carbigdata.teste.domain.address.Address;
import br.com.carbigdata.teste.domain.address.dto.AddressDTO;
import br.com.carbigdata.teste.domain.customer.Customer;
import br.com.carbigdata.teste.domain.customer.dto.CustomerDTO;
import br.com.carbigdata.teste.domain.occurrence.Occurrence;
import br.com.carbigdata.teste.domain.occurrence.PhotoOccurrence;
import br.com.carbigdata.teste.domain.occurrence.dto.OccurrenceDTO;
import br.com.carbigdata.teste.domain.occurrence.dto.PhotoOccurrenceDTO;
import br.com.carbigdata.teste.repository.AddressRepository;
import br.com.carbigdata.teste.repository.CustomerRepository;
import br.com.carbigdata.teste.repository.OccurrenceRepository;
import br.com.carbigdata.teste.service.address.IAddressService;
import br.com.carbigdata.teste.service.customer.ICustomerService;
import br.com.carbigdata.teste.shared.uploadImage.MinioClientProperties;
import br.com.carbigdata.teste.utils.UtilDocuments;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OccurrenceServiceImpl implements IOccurrenceService {

    private final OccurrenceRepository  occurrenceRepository;

    private final AddressRepository addressRepository;

    private final CustomerRepository customerRepository;

    private final UtilDocuments utilDocuments;

    private  final ICustomerService customerService;

    private final IAddressService addressService;

    private final IPhotoOccurrenceService photoOccurrenceService;

    private final MinioClientProperties minioClientProperties;

    @Override
    public OccurrenceDTO createOccurrence(OccurrenceRequestDTO request,Long customerId, Long idAddress) {
        Address address = addressRepository.findById(idAddress).orElseThrow(() -> new Error("Address not found"));

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new Error("Customer not found"));

        Occurrence occurrence = new Occurrence();
        occurrence.setAddress(address);
        occurrence.setCustomer(customer);
        occurrence.setStaOcorrencia(request.getStaOcorrencia());
        occurrence.setDtaOcorrencia(request.getDtaOcorrencia());

        Occurrence occurrenceSaved = occurrenceRepository.saveAndFlush(occurrence);

        return createResponseOccurrence(occurrenceSaved, address, customer);
    }

    @Override
    public OccurrenceDTO updateOccurrence(Long id, UpdateOccurrenceRequestDTO request) {
        Occurrence occurrence = findByIdOccurrence(id);

        if(occurrence.getStaOcorrencia().equals(SITUATION_INCIDENT.FINALIZADA)){
            throw new Error("Ocorrência já finalizada");
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

        return createResponsePhotoOccurrence(occurrence, occurrence.getAddress(), occurrence.getCustomer(),occurrence.getPhotoOccurrences());
    }

    @Override
    public List<OccurrencePaginateResponseDTO> getOccurrencesDetails(int page, int size) {
        List<OccurrencePaginateResponseDTO> responseDTOS = new ArrayList<>();
        OccurrencePaginateResponseDTO occurrencePaginateResponseDTO = new OccurrencePaginateResponseDTO();
        List<OccurrenceDTO> content = new ArrayList<>();

        Pageable pageable = PageRequest.of(page, size);
        Page<Occurrence> occurrenceList = occurrenceRepository.findAll(pageable);

        for(Occurrence occurrence : occurrenceList){
            OccurrenceDTO responseOccurrence = createResponsePhotoOccurrence(occurrence, occurrence.getAddress(), occurrence.getCustomer(), occurrence.getPhotoOccurrences());
            content.add(responseOccurrence);
        }

        occurrencePaginateResponseDTO.setContent(content);
        occurrencePaginateResponseDTO.setTotalPages(occurrenceList.getTotalPages());
        occurrencePaginateResponseDTO.setTotalElements(occurrenceList.getTotalElements());
        responseDTOS.add(occurrencePaginateResponseDTO);
        return responseDTOS;
    }

    @Override
    public void finalizeOccurrence(Long id) {
        Occurrence occurrence = findByIdOccurrence(id);
        if(occurrence.getStaOcorrencia().equals(SITUATION_INCIDENT.FINALIZADA)){
            throw new Error("Ocorrência já finalizada");
        }

        occurrence.setStaOcorrencia(SITUATION_INCIDENT.FINALIZADA);

        occurrenceRepository.saveAndFlush(occurrence);
    }

    @Override
    @Transactional
    public OccurrenceDTO createOccurrenceComplete(List<MultipartFile> imagem, String dadosJson) throws JsonProcessingException {
        OccurrenceCompleteRequestDTO data = convertStringToObj(dadosJson);

        Customer customer = customerRepository.findByNroCpf(utilDocuments.clearDocument(data.getNro_cpf()))
                .orElseGet(() -> {
                    CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO();
                    customerRequestDTO.setDta_nascimento(null);
                    customerRequestDTO.setNme_cliente(utilDocuments.clearDocument(data.getNro_cpf()));
                    customerRequestDTO.setNro_cpf(data.getNro_cpf());

                    CustomerDTO customerSave = customerService.createCustomer(customerRequestDTO);

                    return Customer.builder()
                            .codCliente(customerSave.getCodCliente())
                            .dtaCriacao(customerSave.getDtaCriacao())
                            .dtaNascimento(customerSave.getDtaNascimento())
                            .nmeCliente(customerSave.getNmeCliente())
                            .nroCpf(customerSave.getNroCpf())
                            .build();
                });

        CreateAddressRequestDTO addressRequestDTO = new CreateAddressRequestDTO();
        addressRequestDTO.setNmeCidade(data.getNmeCidade());
        addressRequestDTO.setNmeEstado(data.getNmeEstado());
        addressRequestDTO.setNroCep(data.getNroCep());
        addressRequestDTO.setNmeBairro(data.getNmeBairro());
        addressRequestDTO.setNmeLogradouro(data.getNmeLogradouro());

        AddressDTO address = addressService.createAddress(addressRequestDTO);


        Occurrence occurrence = new Occurrence();
        occurrence.setAddress(Address.builder()
                .codEndereco(address.getCodEndereco())
                        .nmeBairro(address.getNmeBairro())
                        .nmeCidade(address.getNmeCidade())
                        .nmeEstado(address.getNmeEstado())
                        .nroCep(address.getNroCep())
                        .nmeLogradouro(address.getNmeLogradouro())
                .build());
        occurrence.setCustomer(customer);
        occurrence.setStaOcorrencia(SITUATION_INCIDENT.ATIVA);
        occurrence.setDtaOcorrencia(data.getDtaOcorrencia());

        Occurrence occurrenceSaved = occurrenceRepository.saveAndFlush(occurrence);

        List<PhotoOccurrence> photoOccurrenceDTOS = photoOccurrenceService.savePhotoOccurrenceOccurrence(imagem, occurrenceSaved);


        return createResponsePhotoOccurrence(occurrenceSaved, occurrenceSaved.getAddress(), occurrenceSaved.getCustomer(), photoOccurrenceDTOS);
    }

    private OccurrenceCompleteRequestDTO convertStringToObj(String dadosJson) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(dadosJson, OccurrenceCompleteRequestDTO.class);
    }

    private Occurrence findByIdOccurrence(Long id) {
        return occurrenceRepository.findById(id).orElseThrow(() -> new Error("Occurrence not found"));
    }

    private  OccurrenceDTO createResponsePhotoOccurrence(Occurrence occurrence, Address address, Customer customer, List<PhotoOccurrence> photoOccurrences) {
        List<PhotoOccurrenceDTO> photoOccurrencesList = new ArrayList<>();


        for(PhotoOccurrence photoOccurrence : photoOccurrences){
            String urlFinal =  minioClientProperties.getUrl() +
                    "/" +
                    minioClientProperties.getBucket_name() +
                    "/"
                    + photoOccurrence.getDscPathBucket();

            PhotoOccurrenceDTO photoOccurrenceDTO = new PhotoOccurrenceDTO();
            photoOccurrenceDTO.setCodFotoOcorrencia(photoOccurrence.getCodFotoOcorrencia());
            photoOccurrenceDTO.setDtaCriacao(photoOccurrence.getDtaCriacao());
            photoOccurrenceDTO.setDscPathBucket(urlFinal);
            photoOccurrenceDTO.setDscHash(photoOccurrence.getDscHash());

            photoOccurrencesList.add(photoOccurrenceDTO);
        }

        OccurrenceDTO responseOccurrence = createResponseOccurrence(occurrence, address, customer);
        responseOccurrence.setPhotoOccurrence(photoOccurrencesList);

        return responseOccurrence;
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
