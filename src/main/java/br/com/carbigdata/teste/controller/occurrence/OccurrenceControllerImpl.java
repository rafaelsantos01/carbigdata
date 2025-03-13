package br.com.carbigdata.teste.controller.occurrence;

import br.com.carbigdata.teste.controller.occurrence.dto.OccurrencePaginateResponseDTO;
import br.com.carbigdata.teste.controller.occurrence.dto.OccurrenceRequestDTO;
import br.com.carbigdata.teste.domain.occurrence.OccurrenceDTO;
import br.com.carbigdata.teste.service.occurrence.IOccurrenceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/occurrence")
@AllArgsConstructor
public class OccurrenceControllerImpl implements IOccurrenceController {

    private  final IOccurrenceService occurrenceService;

    @Override
    public OccurrenceDTO createOccurrence(OccurrenceRequestDTO request) {
        return occurrenceService.createOccurrence(request);
    }

    @Override
    public OccurrenceDTO updateOccurrence(Long id, OccurrenceRequestDTO request) {
        return occurrenceService.updateOccurrence(id, request);
    }

    @Override
    public void deleteOccurrence(Long id) {
        occurrenceService.deleteOccurrence(id);
    }

    @Override
    public OccurrenceDTO getOccurrence(Long id) {
        return occurrenceService.getOccurrence(id);
    }

    @Override
    public List<OccurrencePaginateResponseDTO> getOccurrences(int page, int size) {
        return occurrenceService.getOccurrences(page, size);
    }
}
