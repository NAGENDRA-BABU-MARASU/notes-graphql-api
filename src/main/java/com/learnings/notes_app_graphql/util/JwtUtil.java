package com.learnings.notes_app_graphql.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class JwtUtil {
    private static final String JWT_SECRET = "secretoftheuniverseisjwtsecretoftheuniverseisjwtsecretoftheuniverseisjwtsecretoftheuniverseisjwtsecretoftheuniverseisjwtsecretoftheuniverseisjwtsecretoftheuniverseisjwtsecretoftheuniverseisjwtsecretoftheuniverseisjwt";

    public String generateToken(Authentication authentication) {
        String username = (String) authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + 3600 * 1000);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET.getBytes())
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(JWT_SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject(); // Subject typically holds the username
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(JWT_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            // Invalid JWT signature or claims
            System.out.println("Invalid JWT signature or claims: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            // JWT is expired
            System.out.println("JWT expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            // JWT is unsupported
            System.out.println("Unsupported JWT: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            // JWT claims are invalid
            System.out.println("Invalid JWT claims: " + e.getMessage());
        } catch (JwtException e) {
            // General JWT parsing exception
            System.out.println("JWT parsing exception: " + e.getMessage());
        }
        throw new AuthenticationCredentialsNotFoundException("Invalid token");
    }
}
