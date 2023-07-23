package com.devkor.study.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Value("${jwt.secretKey}")
    private String secretKey;

    private Long accessTokenExpiredMs = 1000 * 3 * 60l;
    private Long refreshTokenExpiredMs = 1000 * 60 * 60l *24;
    public JwtInfoDto login(String userId, String password){
        return JwtService.createJwt(userId,secretKey,accessTokenExpiredMs, refreshTokenExpiredMs);
    }

}
