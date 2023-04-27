package com.devkor.study.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtService {

    public static String getUserId(String token, String secretKey){
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().get("userId", String.class);
    }
    public static boolean isExpired(String token, String secretKey){
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
    public static String createJwt(String userId, String secretKey, Long expiredMs){ // expired time setting properties에서
        Claims claims = Jwts.claims();
        claims.put("userId", userId);
        return  Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expiredMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
