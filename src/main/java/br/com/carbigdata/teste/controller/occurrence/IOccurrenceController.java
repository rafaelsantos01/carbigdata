package br.com.carbigdata.teste.controller.occurrence;

import br.com.carbigdata.teste.controller.occurrence.dto.OccurrencePaginateResponseDTO;
import br.com.carbigdata.teste.controller.occurrence.dto.OccurrenceRequestDTO;
import br.com.carbigdata.teste.controller.occurrence.dto.UpdateOccurrenceRequestDTO;
import br.com.carbigdata.teste.domain.occurrence.dto.OccurrenceDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Ocorrências", description = "Gerenciamento de ocorrências no sistema")
public interface IOccurrenceController {

    @Operation(
            summary = "Cria uma ocorrência vinculada a um cliente e endereço",
            description = "Registra uma nova ocorrência associada ao cliente e endereço informados."
    )
    @PostMapping("/customer/{id}/address/{idAddress}")
    @ResponseStatus(HttpStatus.CREATED)
    OccurrenceDTO createOccurrence(@Valid @RequestBody OccurrenceRequestDTO request,@PathVariable Long id, @PathVariable Long idAddress);


    @Operation(
            summary = "Cria uma ocorrência com arquivos",
            description = "Permite o envio de arquivos anexos e dados em formato JSON para criar uma ocorrência completa."
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    OccurrenceDTO createOccurrenceComplete(@RequestPart("file") List<MultipartFile> file,
                                           @RequestPart("dados") String dadosJson) throws JsonProcessingException;


    @Operation(
            summary = "Atualiza uma ocorrência",
            description = "Atualiza os dados de uma ocorrência existente com base no ID informado."
    )
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    OccurrenceDTO updateOccurrence(@PathVariable Long id,@Valid @RequestBody UpdateOccurrenceRequestDTO request);


    @Operation(
            summary = "Exclui uma ocorrência",
            description = "Remove uma ocorrência do sistema com base no ID fornecido."
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteOccurrence(@PathVariable Long id);



    @Operation(
            summary = "Busca uma ocorrência pelo ID",
            description = "Retorna os detalhes de uma ocorrência específica com base no ID informado."
    )
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    OccurrenceDTO getOccurrence(@PathVariable Long id);


    @Operation(
            summary = "Lista ocorrências detalhadas paginadas",
            description = "Retorna uma lista paginada de ocorrências com informações detalhadas."
    )
    @GetMapping("/detailed")
    @ResponseStatus(HttpStatus.OK)
    List<OccurrencePaginateResponseDTO> getOccurrencesDetails(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "5") int size);


    @Operation(
            summary = "Finaliza uma ocorrência",
            description = "Marca uma ocorrência como finalizada, sem excluir os dados."
    )
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void finalizeOccurrence(@PathVariable Long id);

}
