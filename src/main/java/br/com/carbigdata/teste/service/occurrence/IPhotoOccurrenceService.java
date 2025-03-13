package br.com.carbigdata.teste.service.occurrence;

import br.com.carbigdata.teste.domain.occurrence.dto.PhotoOccurrenceDTO;
import org.springframework.web.multipart.MultipartFile;

public interface IPhotoOccurrenceService {

    PhotoOccurrenceDTO createPhotoOccurrence( Long id,  MultipartFile file);

    PhotoOccurrenceDTO updatePhotoOccurrence( Long id, MultipartFile file);

    void deletePhotoOccurrence( Long id);
}
