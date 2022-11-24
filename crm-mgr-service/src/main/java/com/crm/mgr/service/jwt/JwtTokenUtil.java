package com.crm.mgr.service.jwt;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

import com.crm.mgr.dto.JwtTokenDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
    public static final String SEPARATOR = "\\.";
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    @Value("${jwt.secret}")
    private String secret;

    private final ObjectMapper objectMapper;

    public JwtTokenUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities());
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public JwtTokenDto decodeJwtToken(String encodedToken) {
        String[] splitEncodedToken = encodedToken.split(SEPARATOR);
        String base64EncodedBody = splitEncodedToken[1];
        org.apache.tomcat.util.codec.binary.Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
        try {
            JwtTokenDto jwtTokenDto = objectMapper.readValue(body, JwtTokenDto.class);
            jwtTokenDto.setDecodedToken(encodedToken);
            return jwtTokenDto;
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
