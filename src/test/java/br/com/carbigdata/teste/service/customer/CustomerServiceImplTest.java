package br.com.carbigdata.teste.service.customer;

import br.com.carbigdata.teste.controller.customer.dto.CustomerPaginatedResponseDTO;
import br.com.carbigdata.teste.controller.customer.dto.CustomerRequestDTO;
import br.com.carbigdata.teste.domain.customer.Customer;
import br.com.carbigdata.teste.domain.customer.dto.CustomerDTO;
import br.com.carbigdata.teste.domain.occurrence.Occurrence;
import br.com.carbigdata.teste.repository.CustomerRepository;
import br.com.carbigdata.teste.utils.UtilDocuments;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private UtilDocuments utilDocuments;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    @DisplayName("Create Customer - success")
    void testCreateCustomerSuccess() {
        CustomerRequestDTO requestDTO =  requestDTO();
        Customer savedCustomer = customerTwo();

        when(customerRepository.findByNroCpf(anyString())).thenReturn(Optional.empty());
        when(utilDocuments.clearDocument(anyString())).thenReturn("11223344556");
        when(customerRepository.saveAndFlush(any(Customer.class))).thenReturn(savedCustomer);

        CustomerDTO result = customerService.createCustomer(requestDTO);

        assertNotNull(result);
        assertEquals("Rafael Oliveira", result.getNmeCliente());
    }

    @Test
    @DisplayName("Create Customer - CPF already exists")
    void testCreateCustomerCpfAlreadyExistsThrowsError() {
        CustomerRequestDTO requestDTO = requestDTO();
        Customer existingCustomer = customerTwo();
        requestDTO.setNro_cpf(existingCustomer.getNroCpf());

        when(customerRepository.findByNroCpf(anyString())).thenReturn(Optional.of(existingCustomer));

        Error exception = assertThrows(Error.class, () -> customerService.createCustomer(requestDTO));
        assertEquals("CPF já cadastrado", exception.getMessage());
    }

    @Test
    @DisplayName("Update Customer - success")
    void testUpdateCustomerSuccess() {
        Long id = 1L;
        CustomerRequestDTO requestDTO = requestDTO();
        Customer existingCustomer = customerTwo();

        when(customerRepository.findById(id)).thenReturn(Optional.of(existingCustomer));
        when(utilDocuments.clearDocument(anyString())).thenReturn("11122233344");
        when(customerRepository.saveAndFlush(any(Customer.class))).thenReturn(existingCustomer);

        CustomerDTO result = customerService.updateCustomer(requestDTO, id);

        assertNotNull(result);
        assertEquals("Carlos Mendes", result.getNmeCliente());
    }

    @Test
    @DisplayName("Update Customer - CPF already exists")
    void testDeleteCustomerSuccess() {
        Long id = 1L;
        Customer customer = customerOne();
        customer.setOccurrenceList(Collections.emptyList());

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        doNothing().when(customerRepository).deleteById(id);

        assertDoesNotThrow(() -> customerService.deleteCustomer(id));
        verify(customerRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Delete Customer - with occurrences")
    void testDeleteCustomerWithOccurrencesThrowsError() {
        Long id = 1L;
        Customer customer = customerOne();
        customer.setOccurrenceList(List.of(new Occurrence()));

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        Error exception = assertThrows(Error.class, () -> customerService.deleteCustomer(id));
        assertEquals("Cliente possui ocorrências cadastradas, finalize e exclua as ocorrências para excluir o cliente", exception.getMessage());
    }

    @Test
    @DisplayName("Get Customer - success")
    void testGetCustomerSuccess() {
        Long id = 1L;
        Customer customer = customerOne();

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        CustomerDTO result = customerService.getCustomer(id);

        assertNotNull(result);
        assertEquals("Paulo Santos", result.getNmeCliente());
    }

    @Test
    @DisplayName("Get Customer - not found")
    void testGetCustomersSuccess() {
        int page = 0, size = 2;
        Customer customer1 = customerOne();
        Customer customer2 = customerTwo();
        Page<Customer> customerPage = new PageImpl<>(List.of(customer1, customer2));

        when(customerRepository.findAll(any(Pageable.class))).thenReturn(customerPage);

        List<CustomerPaginatedResponseDTO> result = customerService.getCustomers(page, size);

        assertNotNull(result);
        assertEquals(2, result.get(0).getContet().size());
        assertEquals("Paulo Santos", result.get(0).getContet().get(0).getNmeCliente());
    }

    private Customer customerOne(){
        Customer customer1 = new Customer();
        customer1.setCodCliente(1L);
        customer1.setNmeCliente("Paulo Santos");
        customer1.setNroCpf("98765432100");
        customer1.setDtaNascimento(null);

        return customer1;

    }

    private Customer customerTwo(){
        Customer customer2 = new Customer();
        customer2.setCodCliente(2L);
        customer2.setNmeCliente("Rafael Oliveira");
        customer2.setNroCpf("11223344556");
        customer2.setDtaNascimento(null);

        return customer2;
    }

    private CustomerRequestDTO requestDTO(){
        CustomerRequestDTO requestDTO = new CustomerRequestDTO();
        requestDTO.setNme_cliente("Carlos Mendes");
        requestDTO.setNro_cpf("111.222.333-44");
        requestDTO.setDta_nascimento(null);

        return requestDTO;
    }

}