package br.com.carbigdata.teste.common.minio;

import br.com.carbigdata.teste.shared.uploadImage.MinioClientProperties;
import io.minio.MinioClient;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class MinioConfig {

    private final MinioClientProperties minioClientProperties;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minioClientProperties.getUrl())
                .credentials(minioClientProperties.getAccess_key(), minioClientProperties.getSecret_key())
                .build();
    }
}