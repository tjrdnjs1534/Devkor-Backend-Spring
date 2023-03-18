package com.devkor.study.users;

import java.util.List;


public interface UserService {
    List<UserDTO> findAllUsers();
    UserDTO findUserById(Long id);
    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);


}
