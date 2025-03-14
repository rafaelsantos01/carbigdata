package br.com.carbigdata.teste.controller.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {

    @NotBlank
    @Schema(example = "admin")
    private String userName;

    @NotBlank
    @Schema(example = "admin")
    private String password;
}
