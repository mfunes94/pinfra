package com.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.SecretKey;

public class JwtUtil {
    private static final long EXPIRATION_TIME = 900000; // 15 minutos en milisegundos
    private static final SecretKey SECRET_KEY;

    static {
        try {
            SECRET_KEY = JWTKeyGenerator.generarClaveSecreta();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al generar la clave secreta", e);
        }
    }

    public static String generateToken(String correo) {
        try {
            String encodedKey = JWTKeyGenerator.encodeKey(SECRET_KEY);

            Date now = new Date();
            Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

            return Jwts.builder()
                    .setSubject(correo)
                    .setIssuedAt(now)
                    .setExpiration(expiration)
                    .signWith(SignatureAlgorithm.HS256, encodedKey)
                    .compact();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean validateToken(String token) {
        try {
            String encodedKey = JWTKeyGenerator.encodeKey(SECRET_KEY);

            Claims claims = Jwts.parser()
                    .setSigningKey(encodedKey)
                    .parseClaimsJws(token)
                    .getBody();

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}


