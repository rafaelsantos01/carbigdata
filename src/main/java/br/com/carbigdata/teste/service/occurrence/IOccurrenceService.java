package br.com.carbigdata.teste.service.occurrence;

import br.com.carbigdata.teste.controller.occurrence.dto.OccurrencePaginateResponseDTO;
import br.com.carbigdata.teste.controller.occurrence.dto.OccurrenceRequestDTO;
import br.com.carbigdata.teste.controller.occurrence.dto.UpdateOccurrenceRequestDTO;
import br.com.carbigdata.teste.domain.occurrence.dto.OccurrenceDTO;

import java.util.List;

public interface IOccurrenceService {

    OccurrenceDTO createOccurrence(OccurrenceRequestDTO request,Long id, Long idAddress);

    OccurrenceDTO updateOccurrence( Long id, UpdateOccurrenceRequestDTO request);

    void deleteOccurrence( Long id);

    OccurrenceDTO getOccurrence( Long id);

    List<OccurrencePaginateResponseDTO> getOccurrencesDetails( int page, int size);

    void finalizeOccurrence(Long id);
}
