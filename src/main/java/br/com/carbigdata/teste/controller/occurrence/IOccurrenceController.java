package br.com.carbigdata.teste.controller.occurrence;

import br.com.carbigdata.teste.controller.occurrence.dto.OccurrencePaginateResponseDTO;
import br.com.carbigdata.teste.controller.occurrence.dto.OccurrenceRequestDTO;
import br.com.carbigdata.teste.controller.occurrence.dto.UpdateOccurrenceRequestDTO;
import br.com.carbigdata.teste.domain.occurrence.dto.OccurrenceDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IOccurrenceController {

    @PostMapping("/customer/{id}/address/{idAddress}")
    @ResponseStatus(HttpStatus.CREATED)
    OccurrenceDTO createOccurrence(@Valid @RequestBody OccurrenceRequestDTO request,@PathVariable Long id, @PathVariable Long idAddress);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    OccurrenceDTO updateOccurrence(@PathVariable Long id,@Valid @RequestBody UpdateOccurrenceRequestDTO request);


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteOccurrence(@PathVariable Long id);


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    OccurrenceDTO getOccurrence(@PathVariable Long id);

    @GetMapping("/detailed")
    @ResponseStatus(HttpStatus.OK)
    List<OccurrencePaginateResponseDTO> getOccurrencesDetails(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "5") int size);

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void finalizeOccurrence(@PathVariable Long id);



}
