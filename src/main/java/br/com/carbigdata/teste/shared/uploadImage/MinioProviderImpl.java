package br.com.carbigdata.teste.shared.uploadImage;

import io.minio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class MinioProviderImpl implements IUploadImageProvider{

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioClientProperties minioClientProperties;


    public String uploadFile(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();

            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioClientProperties.getBucket_name()).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioClientProperties.getBucket_name()).build());
            }
            ObjectWriteResponse objectWriteResponse = minioClient.putObject(
                    PutObjectArgs.builder().bucket(minioClientProperties.getBucket_name()).object(UUID.randomUUID().toString()).stream(
                                    inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());

            return  objectWriteResponse.object();
        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    public void deleteFile(String objectName) {
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioClientProperties.getBucket_name()).build());
            if (!found) {
                throw new Error("Bucket not found");
            }

            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(minioClientProperties.getBucket_name())
                    .object(objectName)
                    .build());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while deleting file: " + e.getMessage());
        }
    }
}
