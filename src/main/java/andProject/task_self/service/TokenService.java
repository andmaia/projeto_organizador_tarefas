package andProject.task_self.service;

import andProject.task_self.model.PersonCredentials;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    public String gerarToken(PersonCredentials personCredentials) {
        try {
            var algoritimo = Algorithm.HMAC256("12345678");
            return JWT.create()
                    .withIssuer("API andProject")
                    .withSubject(personCredentials.getEmail())
                    .sign(algoritimo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("erro ao gerrar token jwt", exception);
        }
    }

    public String validarToken(String tokenJWT){
        try {
            var algoritmo = Algorithm.HMAC256("12345678");
            return JWT.require(algoritmo)
                    .withIssuer("API andProject")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }


    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
