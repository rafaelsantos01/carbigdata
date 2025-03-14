package br.com.carbigdata.teste.shared.uploadImage;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioClientProperties {

    private String url;
    private String access_key;
    private String secret_key;
    private String bucket_name;
}
