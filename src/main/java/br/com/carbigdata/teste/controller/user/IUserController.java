package br.com.carbigdata.teste.controller.user;

import br.com.carbigdata.teste.controller.user.dto.LoginRequestDTO;
import br.com.carbigdata.teste.controller.user.dto.LoginResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;


public interface IUserController {

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO loginRequestDTO);
}
