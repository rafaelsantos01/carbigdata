package br.com.carbigdata.teste.controller.user;

import br.com.carbigdata.teste.controller.user.dto.LoginRequestDTO;
import br.com.carbigdata.teste.controller.user.dto.LoginResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@Tag( name= "Autenticação")
public interface IUserController {

    @Operation(
            summary = "Realiza o login",
            description = "Realiza o login do usuário"
    )
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO loginRequestDTO);
}
