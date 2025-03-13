package br.com.carbigdata.teste.controller.user.customer;

import br.com.carbigdata.teste.controller.user.customer.dto.CustomerRequestDTO;
import br.com.carbigdata.teste.domain.customer.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerControllerImpl implements ICustomerController{

    @Override
    public CustomerDTO createCustomer(CustomerRequestDTO customerDTO) {
        return null;
    }

    @Override
    public CustomerDTO updateCustomer(CustomerRequestDTO customerDTO, Long id) {
        return null;
    }

    @Override
    public void deleteCustomer(Long id) {

    }

    @Override
    public CustomerDTO getCustomer(Long id) {
        return null;
    }

    @Override
    public List<CustomerDTO> getCustomers() {
        return List.of();
    }
}
