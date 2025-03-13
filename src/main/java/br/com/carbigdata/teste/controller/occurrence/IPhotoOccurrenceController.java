package br.com.carbigdata.teste.controller.occurrence;

import br.com.carbigdata.teste.domain.occurrence.dto.PhotoOccurrenceDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

public interface IPhotoOccurrenceController {

    @PostMapping("/occurrence/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    PhotoOccurrenceDTO createPhotoOccurrence(@PathVariable Long id,  @RequestParam("file") MultipartFile file);

    @PutMapping("/{id}")
    PhotoOccurrenceDTO updatePhotoOccurrence(@PathVariable Long id, MultipartFile file);

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletePhotoOccurrence(@PathVariable Long id);

}
