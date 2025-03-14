package br.com.carbigdata.teste.common.exceptions;

import br.com.carbigdata.teste.common.exceptions.dto.ErrorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@Service
public class ErrorAuthenticationHandler {

    @Autowired
    private MessageSource messageSource;

    public void tokenExpired(HttpServletResponse response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        ErrorDTO errorDTO = new ErrorDTO("Acesso expirado", HttpServletResponse.SC_UNAUTHORIZED);

        String json = objectMapper.writeValueAsString(errorDTO);

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(json);
        response.getWriter().flush();
    }

}