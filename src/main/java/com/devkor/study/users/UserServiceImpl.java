package com.devkor.study.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<UserDTO> findAllUsers(){
        List<UserEntity> users= userRepository.findAll(); //entity를 받고 DTO로 바꿔야하나? 아니면 Entity전체를 반환?
        List<UserDTO> userDTOs =new ArrayList<>();
        for(UserEntity user :users){
            UserDTO userDTO = new UserDTO(user.getName(), user.getAge() , user.getUserID(), user.getPassword() );
            //이렇게 변환하는게 맞는건가
            //modelmapper library 찾아보기 entity 수정된다면? toDTO가 가능한가
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    @Override
    public UserDTO findUserById(Long id){
        UserEntity userEntity = userRepository.findById(id).orElseThrow(()->new NoSuchElementException("해당 id의 user가 없습니다."));
        return new UserDTO(userEntity.getName(), userEntity.getAge(), userEntity.getUserID(), userEntity.getPassword());
        //return userDTO;
    } // 없는 회원 조회 시 처리

    @Override
    public UserDTO createUser(UserDTO userDTO){
        UserEntity userEntity = userRepository.save(userDTO.toEntity());
        return new UserDTO(userEntity.getName(), userEntity.getAge(), userEntity.getUserID(), userEntity.getPassword());

    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO){
        userRepository.findById(id).orElseThrow(()->new NoSuchElementException("해당 id의 user가 없습니다."));
        UserEntity userUpdateEntity = UserEntity.builder().id(id).name(userDTO.getName()).age(userDTO.getAge()).userID(userDTO.getUserID()).password(userDTO.getPassword()).build();
        //setter를 지양?, entity 레벨에 새로운 메서드로 넣어도 되는가?
        UserEntity returnEntity = userRepository.save(userUpdateEntity);
        return new UserDTO(returnEntity.getName(), returnEntity.getAge(), returnEntity.getUserID(), returnEntity.getPassword());

    }
    @Override
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }




}
