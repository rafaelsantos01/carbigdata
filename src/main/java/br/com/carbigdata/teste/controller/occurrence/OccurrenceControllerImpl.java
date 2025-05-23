package br.com.carbigdata.teste.controller.occurrence;

import br.com.carbigdata.teste.controller.occurrence.dto.OccurrencePaginateResponseDTO;
import br.com.carbigdata.teste.controller.occurrence.dto.OccurrenceRequestDTO;
import br.com.carbigdata.teste.controller.occurrence.dto.UpdateOccurrenceRequestDTO;
import br.com.carbigdata.teste.domain.occurrence.dto.OccurrenceDTO;
import br.com.carbigdata.teste.service.occurrence.IOccurrenceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/occurrence")
@AllArgsConstructor
public class OccurrenceControllerImpl implements IOccurrenceController {

    private  final IOccurrenceService occurrenceService;

    @Override
    public OccurrenceDTO createOccurrence(OccurrenceRequestDTO request, Long id, Long idAddress) {
        return occurrenceService.createOccurrence(request,id,idAddress);
    }

    @Override
    public OccurrenceDTO createOccurrenceComplete(List<MultipartFile> imagem, String dadosJson) throws JsonProcessingException {
        return occurrenceService.createOccurrenceComplete(imagem,dadosJson);

    }

    @Override
    public OccurrenceDTO updateOccurrence(Long id, UpdateOccurrenceRequestDTO request) {
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
    public List<OccurrencePaginateResponseDTO> getOccurrencesDetails(int page, int size) {
        return occurrenceService.getOccurrencesDetails(page, size);
    }

    @Override
    public void finalizeOccurrence(Long id) {
        occurrenceService.finalizeOccurrence(id);
    }
}
