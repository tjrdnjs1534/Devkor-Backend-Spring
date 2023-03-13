package com.devkor.study.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    UserDataHandler userDataHandler;

    @Autowired
    public UserServiceImpl(UserDataHandler userDataHandler) {
        this.userDataHandler = userDataHandler;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO){
        UserEntity userEntity = userDataHandler.createUserEntity(userDTO);
        UserDTO userDTOReturn = new UserDTO(userEntity.getName(), userEntity.getAge(), userEntity.getUserID(), userEntity.getPassword());

        return userDTOReturn;
    }
}
