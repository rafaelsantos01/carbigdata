package br.com.carbigdata.teste.controller.user.customer;

import br.com.carbigdata.teste.controller.user.customer.dto.CustomerRequestDTO;
import br.com.carbigdata.teste.domain.customer.dto.CustomerDTO;
import br.com.carbigdata.teste.service.customer.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerControllerImpl implements ICustomerController{

    private final ICustomerService customerService;

    @Override
    public CustomerDTO createCustomer(CustomerRequestDTO customerDTO) {
        return customerService.createCustomer(customerDTO);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerRequestDTO customerDTO, Long id) {
        return customerService.updateCustomer(customerDTO, id);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerService.deleteCustomer(id);
    }

    @Override
    public CustomerDTO getCustomer(Long id) {
        return customerService.getCustomer(id);
    }

    @Override
    public List<CustomerDTO> getCustomers() {
        return customerService.getCustomers();
    }
}
