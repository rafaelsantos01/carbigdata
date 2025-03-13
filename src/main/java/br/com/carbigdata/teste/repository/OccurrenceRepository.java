package br.com.carbigdata.teste.repository;

import br.com.carbigdata.teste.domain.occurrence.Occurrence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OccurrenceRepository extends JpaRepository<Occurrence, Long> {

    Page<Occurrence> findAll(Pageable pageable);
}