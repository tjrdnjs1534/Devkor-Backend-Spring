package com.devkor.study.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Value("${jwt.secretKey}")
    private String secretKey;

    private Long expiredMs = 1000 * 60 * 60l;
    public String login(String userId, String password){
        return JwtService.createJwt(userId,secretKey,expiredMs);
    }

}
