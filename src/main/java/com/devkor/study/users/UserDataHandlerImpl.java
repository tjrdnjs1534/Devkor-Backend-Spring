package com.devkor.study.users;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserDataHandlerImpl implements UserDataHandler{
    UserDAO userDAO;

    @Autowired
    public UserDataHandlerImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserEntity createUserEntity(UserDTO userDTO){
        UserEntity userEntity = userDTO.toEntity();
        return userDAO.createUser(userEntity);
    }
}
