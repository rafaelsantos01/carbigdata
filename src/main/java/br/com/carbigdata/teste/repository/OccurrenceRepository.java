package br.com.carbigdata.teste.repository;

import br.com.carbigdata.teste.domain.customer.Customer;
import br.com.carbigdata.teste.domain.occurrence.Occurrence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OccurrenceRepository extends JpaRepository<Occurrence, Long> {

    //TODO - Implementar a consulta de ocorrencias detalhada
    Page<Occurrence> findAll(Pageable pageable);

    void deleteAllByCustomer(Customer customer);
}