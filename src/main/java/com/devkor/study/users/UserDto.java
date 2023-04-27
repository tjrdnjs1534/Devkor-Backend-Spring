package com.devkor.study.users;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDto {
    private String name;
    private int age;
    private String userId;
    private String password;

    public UserEntity toEntity(){
        return UserEntity.builder()
                .name(name)
                .age(age)
                .userId(userId)
                .password(password)
                .build();
    }

}
