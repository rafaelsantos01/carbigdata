package br.com.carbigdata.teste.controller.occurrence;

import br.com.carbigdata.teste.controller.occurrence.dto.CreateFileOccurrenceDTO;
import br.com.carbigdata.teste.domain.occurrence.dto.PhotoOccurrenceDTO;
import br.com.carbigdata.teste.service.occurrence.IPhotoOccurrenceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/photo/occurrence")
@AllArgsConstructor
public class PhotoOccurrenceControllerImpl implements IPhotoOccurrenceController{

    private final IPhotoOccurrenceService photoOccurrenceService;

    @Override
    public CreateFileOccurrenceDTO createPhotoOccurrence(Long id, List<MultipartFile> files) {
        return photoOccurrenceService.createPhotoOccurrence(id, files);
    }

    @Override
    public PhotoOccurrenceDTO updatePhotoOccurrence(Long id, MultipartFile file) {
        return photoOccurrenceService.updatePhotoOccurrence(id, file);
    }

    @Override
    public void deletePhotoOccurrence(Long id) {
        photoOccurrenceService.deletePhotoOccurrence(id);
    }
}
