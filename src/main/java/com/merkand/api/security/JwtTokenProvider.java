package com.merkand.api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${token.signing.key}")
    private String secret;

    @Value("${token.signing.expiration-ms}")
    private long validityInMilliseconds;

    private Key key;

    @PostConstruct
    public void init() {
        if (secret == null || secret.isBlank()) {
            throw new IllegalStateException("Propertie'token.signing.key' is not set");
        }
        if (secret.getBytes(StandardCharsets.UTF_8).length < 32) {
            throw new IllegalStateException("token.signing.key must have at least 32 bytes for HS256");
        }
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UserDetails userDetails) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + validityInMilliseconds);

        String roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles", roles)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Authentication getAuthentication(String token, UserDetails userDetails) {
        Collection<GrantedAuthority> authorities = Arrays.stream(getRolesFromToken(token))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public String[] getRolesFromToken(String token) {
        String roles = (String) getClaims(token).get("roles");
        if (roles == null || roles.isBlank()) return new String[0];
        return roles.split(",");
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = getClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception ex) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}

