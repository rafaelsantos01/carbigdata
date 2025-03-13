package br.com.carbigdata.teste.controller.occurrence;

import br.com.carbigdata.teste.controller.occurrence.dto.OccurrencePaginateResponseDTO;
import br.com.carbigdata.teste.controller.occurrence.dto.OccurrenceRequestDTO;
import br.com.carbigdata.teste.domain.occurrence.dto.OccurrenceDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IOccurrenceController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    OccurrenceDTO createOccurrence(@Valid @RequestBody OccurrenceRequestDTO request);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    OccurrenceDTO updateOccurrence(@PathVariable Long id,@Valid @RequestBody OccurrenceRequestDTO request);


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteOccurrence(@PathVariable Long id);


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    OccurrenceDTO getOccurrence(@PathVariable Long id);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<OccurrencePaginateResponseDTO> getOccurrences(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "5") int size);

}
