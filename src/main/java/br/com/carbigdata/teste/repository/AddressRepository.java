package br.com.carbigdata.teste.repository;

import br.com.carbigdata.teste.domain.address.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Page<Address> findAll(Pageable pageable);
}