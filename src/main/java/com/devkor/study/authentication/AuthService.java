package com.devkor.study.authentication;

public interface AuthService {
    JwtInfoDto login(String userId, String password);
    //void logout();


}
