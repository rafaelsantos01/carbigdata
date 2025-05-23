package br.com.carbigdata.teste.common.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("Server URL in Development environment");


        Contact contact = new Contact();
        contact.setEmail("rafael.p.santos@outlook.com.br");
        contact.setName("Rafael");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("CarBigData API")
                .version("0.0.1")
                .contact(contact)
                .description("Esta API fornece endpoints para gerenciamento e análise de dados relacionados a veículos na plataforma CarBigData.")
                .termsOfService("https://www.termos.com/terms")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}