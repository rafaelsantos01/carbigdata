package br.com.carbigdata.teste.controller.occurrence;

import br.com.carbigdata.teste.controller.occurrence.dto.CreateFileOccurrenceDTO;
import br.com.carbigdata.teste.domain.occurrence.dto.PhotoOccurrenceDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPhotoOccurrenceController {

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    CreateFileOccurrenceDTO createPhotoOccurrence(@PathVariable Long id, @RequestParam("file") List<MultipartFile> files);

    @PutMapping("/{id}")
    PhotoOccurrenceDTO updatePhotoOccurrence(@PathVariable Long id, MultipartFile file);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletePhotoOccurrence(@PathVariable Long id);

}
