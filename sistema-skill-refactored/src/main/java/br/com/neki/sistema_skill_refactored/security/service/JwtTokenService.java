package br.com.neki.sistema_skill_refactored.security.service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.neki.sistema_skill_refactored.domain.User;

@Service
public class JwtTokenService {

	@Value("${SECRET.KEY}")
	private String secretKey;

	@Value("${ISSUER}")
	private String issuer;

	public String generateToken(User user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secretKey);
			return JWT.create().withIssuer(issuer).withIssuedAt(new Date()).withExpiresAt(expirationDate())
					.withSubject(user.getUsername()).withClaim("id", user.getUserId().toString()).sign(algorithm);
		} catch (JWTCreationException exception) {
			throw new JWTCreationException("Error generating token", exception);
		}
	}

	public String getSubjectFromToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secretKey);
			return JWT.require(algorithm).withIssuer(issuer).build().verify(token).getSubject();
		} catch (JWTVerificationException exception) {
			throw new JWTVerificationException("Invalid or expired token");
		}
	}
	
	public Integer getUserIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getClaim("userId").asInt();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Invalid or expired token");
        }
    }

	private Instant expirationDate() {
		return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(2).toInstant();
	}
}

