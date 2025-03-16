package br.com.carbigdata.teste.repository;

import br.com.carbigdata.teste.domain.occurrence.PhotoOccurrence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoOccurrenceRepository extends JpaRepository<PhotoOccurrence, Long> {
}