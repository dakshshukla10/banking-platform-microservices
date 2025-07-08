package com.example.auth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    private final SecretKey key;        // HS256 key (256-bit)

    /**
     * @param b64Secret 32-byte Base64 string configured in application.yml
     *
     *
     */
    public JwtUtil(@Value("${jwt.secret}") String b64Secret) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(b64Secret));
    }

    /** Create a JWT valid for 4 hours that stores username + roles. */
    public String generate(String username, List<String> roles) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(username)                       // <— JJWT 0.12 method
                .claim("roles", roles)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(4 * 60 * 60)))
                .signWith(key)                          // alg inferred (HS256)
                .compact();
    }

    /** Parse & verify signature; throws if token is invalid/expired. */
    public Jws<Claims> parse(String token) {
        return Jwts.parser()                               // JJWT 0.12 builder
                .verifyWith(key)                        // signature key
                .build()
                .parseSignedClaims(token);              // replaces parseClaimsJws
    }
}