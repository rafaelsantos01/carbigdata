package br.com.carbigdata.teste.common.jwt;

import br.com.carbigdata.teste.domain.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class JwtService {

    @Value("${api.security.token.secret}")
    private String secret;


    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("CarBigData-API")
                    .withSubject(user.getCodUsuario().toString())
                    .withClaim("type", "access")
                    .withIssuedAt(new Date())
                    .withExpiresAt(genExpirationAccessToken())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }


    public boolean validateToken(String token) {
        DecodedJWT decodeJWT = decodedToken(token);
        try {
            return !decodeJWT.getExpiresAt().before(new Date());
        } catch (Exception e) {
            throw new Error("InvalidToken");
        }
    }

    public DecodedJWT decodedToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }

    public String getAuthentication(String token){
        DecodedJWT decodedJWT = decodedToken(token);
        return decodedJWT.getSubject();
    }


    private Instant genExpirationAccessToken(){
        return LocalDateTime.now().plusMinutes(30).toInstant(ZoneOffset.of("-03:00"));
    }

}
