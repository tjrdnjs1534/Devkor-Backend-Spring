package com.devkor.study.authentication.google;

import com.devkor.study.users.UserEntity;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;

    public SessionUser(UserEntity user) {
        this.name = user.getName();
        this.email = user.getUserId();
    }
}