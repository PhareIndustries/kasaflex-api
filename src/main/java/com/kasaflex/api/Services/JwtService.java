package com.kasaflex.api.Services;

import com.kasaflex.api.Entities.Utilisateur;
import com.kasaflex.api.Security.AuthContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    public static final String TYPE_USER = "UTILISATEUR";
    public static final String TYPE_CLIENT = "CLIENT";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(Utilisateur utilisateur) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .subject(utilisateur.getIdUtilisateur())
                .claim("id", utilisateur.getIdUtilisateur())
                .claim("mail", utilisateur.getMail())
                .claim("role", utilisateur.getRole().getNomRole())
                .claim("type", TYPE_USER)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(getSignKey())
                .compact();
    }

    public AuthContext parseToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return new AuthContext(
                claims.getSubject(),
                claims.get("mail", String.class),
                claims.get("role", String.class),
                claims.get("type", String.class)
        );
    }

    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
}
