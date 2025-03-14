package br.com.carbigdata.teste.controller.occurrence;

import br.com.carbigdata.teste.controller.occurrence.dto.CreateFileOccurrenceDTO;
import br.com.carbigdata.teste.domain.occurrence.dto.PhotoOccurrenceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Fotos de Ocorrências", description = "Gerenciamento de fotos associadas a ocorrências")
public interface IPhotoOccurrenceController {


    @Operation(
            summary = "Adiciona fotos a uma ocorrência",
            description = "Permite o upload de uma ou mais fotos associadas a uma ocorrência específica."
    )
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    CreateFileOccurrenceDTO createPhotoOccurrence(@PathVariable Long id, @RequestParam("file") List<MultipartFile> files);


    @Operation(
            summary = "Atualiza uma foto da ocorrência",
            description = "Substitui uma foto existente associada a uma ocorrência."
    )
    @PutMapping("/{id}")
    PhotoOccurrenceDTO updatePhotoOccurrence(@PathVariable Long id, MultipartFile file);


    @Operation(
            summary = "Remove uma foto da ocorrência",
            description = "Exclui uma foto específica associada a uma ocorrência."
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletePhotoOccurrence(@PathVariable Long id);

}
