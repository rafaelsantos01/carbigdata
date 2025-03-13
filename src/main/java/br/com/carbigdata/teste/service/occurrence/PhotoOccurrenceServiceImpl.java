package br.com.carbigdata.teste.service.occurrence;

import br.com.carbigdata.teste.domain.occurrence.dto.PhotoOccurrenceDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class PhotoOccurrenceServiceImpl implements IPhotoOccurrenceService {


    @Override
    public PhotoOccurrenceDTO createPhotoOccurrence(Long id, MultipartFile file) {
        return null;
    }

    @Override
    public PhotoOccurrenceDTO updatePhotoOccurrence(Long id, MultipartFile file) {
        return null;
    }

    @Override
    public void deletePhotoOccurrence(Long id) {

    }
}
