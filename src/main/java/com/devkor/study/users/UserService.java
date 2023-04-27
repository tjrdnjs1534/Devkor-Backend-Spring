package com.devkor.study.users;

import java.util.List;

public interface UserService {
    List<UserDto> findAllUsers();
    UserDto findUserById(Long id);
    UserDto createUser(UserDto userDto);

    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);


}
