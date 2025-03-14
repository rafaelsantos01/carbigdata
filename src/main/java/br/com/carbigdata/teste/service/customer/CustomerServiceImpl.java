package br.com.carbigdata.teste.service.customer;

import br.com.carbigdata.teste.controller.customer.dto.CustomerPaginatedResponseDTO;
import br.com.carbigdata.teste.controller.customer.dto.CustomerRequestDTO;
import br.com.carbigdata.teste.domain.customer.Customer;
import br.com.carbigdata.teste.domain.customer.dto.CustomerDTO;
import br.com.carbigdata.teste.repository.CustomerRepository;
import br.com.carbigdata.teste.repository.OccurrenceRepository;
import br.com.carbigdata.teste.repository.PhotoOccurrenceRepository;
import br.com.carbigdata.teste.utils.UtilDocuments;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService{

    private final CustomerRepository customerRepository;

    private final UtilDocuments utilDocuments;

    private final OccurrenceRepository occurrenceRepository;

    private final PhotoOccurrenceRepository photoOccurrenceRepository;

    @Override
    public CustomerDTO createCustomer(CustomerRequestDTO data) {
        customerRepository.findByNroCpf(data.getNro_cpf()).ifPresent(customer -> {
            throw new Error("CPF já cadastrado");
        });

        Customer customer = new Customer();
        customer.setNmeCliente(data.getNme_cliente());
        customer.setNroCpf(utilDocuments.clearDocument(data.getNro_cpf()));
        customer.setDtaNascimento(data.getDta_nascimento());


        Customer customerSaved = customerRepository.saveAndFlush(customer);

        return  createResponseCustomer(customerSaved);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerRequestDTO customerDTO, Long id) {
        Customer customerOptional = findCustomerById(id);

        customerRepository.findByNroCpf(customerDTO.getNro_cpf()).ifPresent(customer -> {
            if(!customer.getCodCliente().equals(customerOptional.getCodCliente())){
                throw new Error("CPF já cadastrado");
            }
        });

        customerOptional.setNmeCliente(customerDTO.getNme_cliente());
        customerOptional.setNroCpf(utilDocuments.clearDocument(customerDTO.getNro_cpf()));
        customerOptional.setDtaNascimento(customerDTO.getDta_nascimento());

        Customer customerSaved = customerRepository.saveAndFlush(customerOptional);

        return createResponseCustomer(customerSaved);
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        Customer customer = findCustomerById(id);

        if(customer.getOccurrenceList().isEmpty()){
             customerRepository.deleteById(customer.getCodCliente());
        }else{
            throw new Error("Cliente possui ocorrências cadastradas, finalize e exclua as ocorrências para excluir o cliente");
        }

    }

    @Override
    public CustomerDTO getCustomer(Long id) {
       Customer customerOptional = findCustomerById(id);

        return createResponseCustomer(customerOptional);
    }

    @Override
    public List<CustomerPaginatedResponseDTO> getCustomers(int page, int size) {
        List<CustomerPaginatedResponseDTO> customerPaginatedResponseDTOS = new ArrayList<>();
        List<CustomerDTO> content = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, size);

        Page<Customer> customerPage = customerRepository.findAll(pageable);

        for(Customer customer : customerPage.getContent()){
            content.add(createResponseCustomer(customer));
        }

        CustomerPaginatedResponseDTO customerPaginatedResponseDTO = new CustomerPaginatedResponseDTO();
        customerPaginatedResponseDTO.setContet(content);
        customerPaginatedResponseDTO.setTotalPages(customerPage.getTotalPages());
        customerPaginatedResponseDTO.setTotalElements(customerPage.getTotalElements());

        customerPaginatedResponseDTOS.add(customerPaginatedResponseDTO);

        return customerPaginatedResponseDTOS;
    }

    private Customer findCustomerById(Long id){
        return customerRepository.findById(id).orElseThrow(() -> new Error("Cliente não encontrado"));
    }


    private CustomerDTO createResponseCustomer( Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCodCliente(customer.getCodCliente());
        customerDTO.setNmeCliente(customer.getNmeCliente());
        customerDTO.setNroCpf(utilDocuments.maskCpf(customer.getNroCpf()));
        customerDTO.setDtaNascimento(customer.getDtaNascimento());
        customerDTO.setDtaCriacao(customer.getDtaCriacao());

        return customerDTO;
    }
}
