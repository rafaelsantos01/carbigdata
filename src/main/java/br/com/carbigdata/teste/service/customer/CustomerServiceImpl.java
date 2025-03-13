package br.com.carbigdata.teste.service.customer;

import br.com.carbigdata.teste.controller.user.customer.dto.CustomerRequestDTO;
import br.com.carbigdata.teste.domain.customer.Customer;
import br.com.carbigdata.teste.domain.customer.dto.CustomerDTO;
import br.com.carbigdata.teste.repository.CustomerRepository;
import br.com.carbigdata.teste.utils.UtilDocuments;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService{

    private final CustomerRepository customerRepository;

    private final UtilDocuments utilDocuments;

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

        return null;
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customerOptional = findCustomerById(id);

        customerRepository.deleteById(customerOptional.getCodCliente());
    }

    @Override
    public CustomerDTO getCustomer(Long id) {
       Customer customerOptional = findCustomerById(id);

        return createResponseCustomer(customerOptional);
    }

    @Override
    public List<CustomerDTO> getCustomers() {
        return List.of();
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
