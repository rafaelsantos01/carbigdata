package br.com.carbigdata.teste.repository;

import br.com.carbigdata.teste.domain.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByNroCpf(String cpf);

    Page<Customer> findAll(Pageable pageable);
}