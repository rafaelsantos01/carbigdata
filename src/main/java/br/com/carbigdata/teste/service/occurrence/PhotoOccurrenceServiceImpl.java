package br.com.carbigdata.teste.service.occurrence;

import br.com.carbigdata.teste.ENUM.SITUATION_INCIDENT;
import br.com.carbigdata.teste.controller.occurrence.dto.CreateFileOccurrenceDTO;
import br.com.carbigdata.teste.domain.occurrence.Occurrence;
import br.com.carbigdata.teste.domain.occurrence.PhotoOccurrence;
import br.com.carbigdata.teste.domain.occurrence.dto.PhotoOccurrenceDTO;
import br.com.carbigdata.teste.repository.OccurrenceRepository;
import br.com.carbigdata.teste.repository.PhotoOccurrenceRepository;
import br.com.carbigdata.teste.shared.uploadImage.IUploadImageProvider;
import br.com.carbigdata.teste.shared.uploadImage.MinioClientProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PhotoOccurrenceServiceImpl implements IPhotoOccurrenceService {

    private final IUploadImageProvider uploadImageProvider;

    private final PhotoOccurrenceRepository photoOccurrenceRepository;

    private final OccurrenceRepository occurrenceRepository;

    private final MinioClientProperties minioClientProperties;


    @Override
    public CreateFileOccurrenceDTO createPhotoOccurrence(Long id, List<MultipartFile> files) {
        List<PhotoOccurrenceDTO> photos = new ArrayList<>();

        Occurrence occurrence = occurrenceRepository.findById(id).orElseThrow(() -> new RuntimeException("Occurrence not found"));
        if(occurrence.getStaOcorrencia().equals(SITUATION_INCIDENT.FINALIZADA)){
            throw new Error("Ocorrência já finalizada");
        }

        for(MultipartFile file: files){
            String fileName = uploadImageProvider.uploadFile(file);
            PhotoOccurrence photoOccurrence = new PhotoOccurrence();

            photoOccurrence.setOccurrence(occurrence);
            photoOccurrence.setDscPathBucket(fileName);
            photoOccurrence.setDscHash("hash");

            PhotoOccurrence photoOccurrenceNew = photoOccurrenceRepository.saveAndFlush(photoOccurrence);

            photos.add(createResponsePhotoOccurrence(photoOccurrenceNew));
        }
        CreateFileOccurrenceDTO responsePhotoOccurrence = createResponseOccurrence(occurrence);
        responsePhotoOccurrence.setPhotos(photos);

        return  responsePhotoOccurrence;
    }

    @Override
    public PhotoOccurrenceDTO updatePhotoOccurrence(Long id, MultipartFile file) {
        return null;
    }

    @Override
    public void deletePhotoOccurrence(Long id) {
    }



    private PhotoOccurrence findByOccurrenceId(Long id) {
        return photoOccurrenceRepository.findById(id).orElseThrow(() -> new RuntimeException("Photo Occurrence not found"));
    }

    private CreateFileOccurrenceDTO createResponseOccurrence(Occurrence occurrence) {
        CreateFileOccurrenceDTO occurrenceDTO = new CreateFileOccurrenceDTO();
        occurrenceDTO.setCodOcorrencia(occurrence.getCodOcorrencia());
        occurrenceDTO.setDtaOcorrencia(occurrence.getDtaOcorrencia());
        occurrenceDTO.setStaOcorrencia(occurrence.getStaOcorrencia());

        return occurrenceDTO;
    }

    private PhotoOccurrenceDTO createResponsePhotoOccurrence(PhotoOccurrence photoOccurrenceNew){
        String urlFinal =  minioClientProperties.getUrl() +
                "/" +
                minioClientProperties.getBucket_name() +
                "/"
                + photoOccurrenceNew.getDscPathBucket();

        PhotoOccurrenceDTO photoOccurrenceDTO = new PhotoOccurrenceDTO();
        photoOccurrenceDTO.setDtaCriacao(photoOccurrenceNew.getDtaCriacao());
        photoOccurrenceDTO.setDscPathBucket(urlFinal);
        photoOccurrenceDTO.setDscHash(photoOccurrenceNew.getDscHash());
        photoOccurrenceDTO.setCodFotoOcorrencia(photoOccurrenceNew.getCodFotoOcorrencia());

        return photoOccurrenceDTO;

    }


}
