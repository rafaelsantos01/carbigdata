package br.com.carbigdata.teste.common.security;

import br.com.carbigdata.teste.common.exceptions.ErrorAuthenticationHandler;
import br.com.carbigdata.teste.common.jwt.JwtService;
import br.com.carbigdata.teste.domain.user.User;
import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService tokenService;

    @Autowired
    private ErrorAuthenticationHandler  errorAuthenticationHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = recoverToken(request);
            if (token != null && tokenService.validateToken(token)) {
                String login = tokenService.getAuthentication(token);

                if (login != null) {
                    setContextAuthentication(login, token);
                }
            }
        }catch (TokenExpiredException exception) {
            errorAuthenticationHandler.tokenExpired(response);
            return;
        } catch (InsufficientAuthenticationException exception) {
            throw new Error("Error in authentication");
        }

        filterChain.doFilter(request, response);
    }

    private void setContextAuthentication(String login, String token) {
        User user = new User();
        user.setCodUsuario(Long.valueOf(login));

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null, null);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


    private String recoverToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null ) {
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }
}
