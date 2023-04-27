package com.devkor.study.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JwtInfoDto {

    private String grantType;
    private String accessToken;
    private String refreshToken;
}
