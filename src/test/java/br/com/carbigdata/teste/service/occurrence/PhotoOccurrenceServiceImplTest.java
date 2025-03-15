package br.com.carbigdata.teste.service.occurrence;

import br.com.carbigdata.teste.ENUM.SITUATION_INCIDENT;
import br.com.carbigdata.teste.domain.occurrence.Occurrence;
import br.com.carbigdata.teste.domain.occurrence.PhotoOccurrence;
import br.com.carbigdata.teste.domain.occurrence.dto.PhotoOccurrenceDTO;
import br.com.carbigdata.teste.repository.OccurrenceRepository;
import br.com.carbigdata.teste.repository.PhotoOccurrenceRepository;
import br.com.carbigdata.teste.shared.uploadImage.IUploadImageProvider;
import br.com.carbigdata.teste.shared.uploadImage.MinioClientProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PhotoOccurrenceServiceImplTest {

    @Mock
    private IUploadImageProvider uploadImageProvider;
    @Mock
    private PhotoOccurrenceRepository photoOccurrenceRepository;
    @Mock
    private  OccurrenceRepository occurrenceRepository;
    @Mock
    private MinioClientProperties minioClientProperties;

    @InjectMocks
    private PhotoOccurrenceServiceImpl photoOccurrenceService;

    @Test
    @DisplayName("Create photo occurrence - Success")
    void createPhotoOccurrence() {
        Long id = 1L;

        when( uploadImageProvider.uploadFile(any(MultipartFile.class))).thenReturn("image");
        when(occurrenceRepository.findById(id)).thenReturn(Optional.of(createOccurrence()));
        when( photoOccurrenceRepository.saveAndFlush(any(PhotoOccurrence.class))).thenReturn(new PhotoOccurrence());

        photoOccurrenceService.createPhotoOccurrence(id,listFile());
    }

    @Test
    @DisplayName("Error Creating a new photo occurrence not found - Error")
    void createPhotoOccurrenceErrorOccurrenceNotFound() {
        Long id = 1L;
        Occurrence occurrence = createOccurrence();
        occurrence.setStaOcorrencia(SITUATION_INCIDENT.FINALIZADA);


        Error exception = assertThrows(Error.class, () ->   photoOccurrenceService.createPhotoOccurrence(id,listFile()));
        assertEquals("Ocorrência não encontrada", exception.getMessage());
    }

    @Test
    @DisplayName("Error Creating a new photo event completed  - Error")
    void createPhotoOccurrenceError() {
        Long id = 1L;
        Occurrence occurrence = createOccurrence();
        occurrence.setStaOcorrencia(SITUATION_INCIDENT.FINALIZADA);

        when(occurrenceRepository.findById(id)).thenReturn(Optional.of(occurrence));

        Error exception = assertThrows(Error.class, () ->   photoOccurrenceService.createPhotoOccurrence(id,listFile()));
        assertEquals("Ocorrência já finalizada", exception.getMessage());
    }


    @Test
    @DisplayName("Update PhotoOccurrence - Success")
    void testUpdatePhotoOccurrenceSuccess() {
        Long id = 1L;
        MultipartFile file = mock(MultipartFile.class);

        PhotoOccurrence existingPhoto = new PhotoOccurrence();
        existingPhoto.setCodFotoOcorrencia(id);
        existingPhoto.setDscPathBucket("old/path.jpg");

        when(photoOccurrenceRepository.findById(id)).thenReturn(Optional.of(existingPhoto));
        when(uploadImageProvider.uploadFile(any(MultipartFile.class))).thenReturn("new/path.jpg");
        when(photoOccurrenceRepository.saveAndFlush(any(PhotoOccurrence.class))).thenAnswer(invocation -> invocation.getArgument(0));

        PhotoOccurrenceDTO result = photoOccurrenceService.updatePhotoOccurrence(id, file);

        assertNotNull(result);
        assertEquals("null/null/new/path.jpg", result.getDscPathBucket());
    }

    @Test
    @DisplayName("Update PhotoOccurrence - Error")
    void testUpdatePhotoOccurrenceNotFound() {
        Long id = 1L;
        MultipartFile file = mock(MultipartFile.class);

        when(photoOccurrenceRepository.findById(id)).thenReturn(Optional.empty());

        Error exception = assertThrows(Error.class, () -> photoOccurrenceService.updatePhotoOccurrence(id, file));
        assertEquals("Foto não encontrada", exception.getMessage());
    }

    @Test
    @DisplayName("Delete Photo Occurrence - Success")
    void testDeletePhotoOccurrenceSuccess() {
        Long id = 1L;
        PhotoOccurrence photoOccurrence = new PhotoOccurrence();
        photoOccurrence.setDscPathBucket("path-to-file.jpg");

        when(photoOccurrenceRepository.findById(id)).thenReturn(Optional.of(photoOccurrence));
        doNothing().when(uploadImageProvider).deleteFile(photoOccurrence.getDscPathBucket());
        doNothing().when(photoOccurrenceRepository).delete(photoOccurrence);

        assertDoesNotThrow(() -> photoOccurrenceService.deletePhotoOccurrence(id));
    }

    @Test
    @DisplayName("Delete Photo Occurrence - Error")
    void testDeletePhotoOccurrenceNotFound() {
        Long id = 1L;
        when(photoOccurrenceRepository.findById(id)).thenReturn(Optional.empty());

        Error exception = assertThrows(Error.class, () -> photoOccurrenceService.deletePhotoOccurrence(id));
        assertEquals("Photo Occurrence not found", exception.getMessage());
    }

    @Test
    @DisplayName("Save Photo Occurrence - Success")
    void testSavePhotoOccurrenceSuccess() {
        Occurrence occurrence = new Occurrence();
        MultipartFile file1 = mock(MultipartFile.class);
        MultipartFile file2 = mock(MultipartFile.class);
        List<MultipartFile> files = List.of(file1, file2);

        when(uploadImageProvider.uploadFile(file1)).thenReturn("file1.jpg");
        when(uploadImageProvider.uploadFile(file2)).thenReturn("file2.jpg");

        when(photoOccurrenceRepository.saveAndFlush(any(PhotoOccurrence.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        List<PhotoOccurrence> result = photoOccurrenceService.savePhotoOccurrenceOccurrence(files, occurrence);

        assertNotNull(result);
    }



    private Occurrence createOccurrence() {
        Occurrence occurrence = new Occurrence();
        occurrence.setCodOcorrencia(1L);
        occurrence.setStaOcorrencia(SITUATION_INCIDENT.ATIVA);

        return occurrence;
    }

    private List<MultipartFile> listFile(){
        MultipartFile fileMock = mock(MultipartFile.class);
        List<MultipartFile> files = List.of(fileMock);

        return files;
    }

}