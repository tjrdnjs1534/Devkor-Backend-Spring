package com.devkor.study.users;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDTO {
    private String name;
    private int age;
    private String userID;
    private String password;

    public UserEntity toEntity(){
        return UserEntity.builder()
                .name(name)
                .age(age)
                .userID(userID)
                .password(password)
                .build();
    }
}
