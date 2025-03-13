package br.com.carbigdata.teste.controller.occurrence;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IOccurrenceController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Object createOccurrence(@Valid @RequestBody Object request);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Object updateOccurrence(@PathVariable Long id,@Valid @RequestBody Object request);


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteOccurrence(@PathVariable Long id);


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Object getOccurrence(@PathVariable Long id);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Object> getOccurrences(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size);

}
