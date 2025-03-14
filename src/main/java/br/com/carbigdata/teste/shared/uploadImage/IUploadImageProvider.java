package br.com.carbigdata.teste.shared.uploadImage;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadImageProvider {

    String uploadFile(MultipartFile file);

    void deleteFile(String objectName);
}
