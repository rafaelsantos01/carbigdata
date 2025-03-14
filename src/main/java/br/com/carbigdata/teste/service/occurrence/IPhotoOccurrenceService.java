package br.com.carbigdata.teste.service.occurrence;

import br.com.carbigdata.teste.controller.occurrence.dto.CreateFileOccurrenceDTO;
import br.com.carbigdata.teste.domain.occurrence.dto.PhotoOccurrenceDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPhotoOccurrenceService {

    CreateFileOccurrenceDTO createPhotoOccurrence(Long id, List<MultipartFile> files);

    PhotoOccurrenceDTO updatePhotoOccurrence( Long id, MultipartFile file);

    void deletePhotoOccurrence( Long id);
}
