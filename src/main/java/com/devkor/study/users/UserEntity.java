package com.devkor.study.users;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder()
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String userId;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public UserEntity update(String name){
        this.name=name;
        return this;
    }
    public String getRoleKey(){
        return this.role.getKey();
    }
}
