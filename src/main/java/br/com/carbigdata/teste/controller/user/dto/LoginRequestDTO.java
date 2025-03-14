package br.com.carbigdata.teste.controller.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {

    @NotBlank
    private String user_name;

    @NotBlank
    private String password;
}
