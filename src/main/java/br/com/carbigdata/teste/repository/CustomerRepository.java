package br.com.carbigdata.teste.repository;

import br.com.carbigdata.teste.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}