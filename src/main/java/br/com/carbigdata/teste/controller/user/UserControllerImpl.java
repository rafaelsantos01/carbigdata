package br.com.carbigdata.teste.controller.user;

import br.com.carbigdata.teste.common.jwt.JwtService;
import br.com.carbigdata.teste.controller.user.dto.LoginRequestDTO;
import br.com.carbigdata.teste.controller.user.dto.LoginResponseDTO;
import br.com.carbigdata.teste.domain.user.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class UserControllerImpl implements IUserController {

    private final AuthenticationManager authenticationManager;

    private final JwtService tokenService;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        try {
            UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(loginRequestDTO.getUser_name(), loginRequestDTO.getPassword());
            Authentication auth = authenticationManager.authenticate(usernamePassword);

            String accessToken = tokenService.generateToken((User) auth.getPrincipal());

            return new LoginResponseDTO(accessToken);
        }catch (Exception e) {
            throw new Error("Falha ao realizar o login");
        }
    }
}
