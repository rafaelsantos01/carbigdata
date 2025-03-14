package br.com.carbigdata.teste.service.occurrence;

import br.com.carbigdata.teste.controller.occurrence.dto.OccurrenceCompleteRequestDTO;
import br.com.carbigdata.teste.controller.occurrence.dto.OccurrencePaginateResponseDTO;
import br.com.carbigdata.teste.controller.occurrence.dto.OccurrenceRequestDTO;
import br.com.carbigdata.teste.controller.occurrence.dto.UpdateOccurrenceRequestDTO;
import br.com.carbigdata.teste.domain.occurrence.dto.OccurrenceDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IOccurrenceService {

    OccurrenceDTO createOccurrence(OccurrenceRequestDTO request,Long id, Long idAddress);

    OccurrenceDTO updateOccurrence( Long id, UpdateOccurrenceRequestDTO request);

    void deleteOccurrence( Long id);

    OccurrenceDTO getOccurrence( Long id);

    List<OccurrencePaginateResponseDTO> getOccurrencesDetails( int page, int size);

    void finalizeOccurrence(Long id);

    OccurrenceDTO createOccurrenceComplete(List<MultipartFile> imagem, String dados) throws JsonProcessingException;
}
