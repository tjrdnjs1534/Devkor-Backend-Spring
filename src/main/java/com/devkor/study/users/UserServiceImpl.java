package com.devkor.study.users;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public List<UserDto> findAllUsers(){
        List<UserEntity> users= userRepository.findAll(); //entity를 받고 DTO로 바꿔야하나? 아니면 Entity전체를 반환?
        List<UserDto> userDtos =new ArrayList<>();
        for(UserEntity user :users){
            UserDto userDto = new UserDto(user.getName(), user.getAge() , user.getUserId(), user.getPassword() );
            //이렇게 변환하는게 맞는건가
            //modelmapper library 찾아보기 entity 수정된다면? toDTO가 가능한가
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    public UserDto findUserById(Long id){
        UserEntity userEntity = userRepository.findById(id).orElseThrow(()->new NoSuchElementException("해당 id의 user가 없습니다."));
        return new UserDto(userEntity.getName(), userEntity.getAge(), userEntity.getUserId(), userEntity.getPassword());
        //return userDTO;
    } // 없는 회원 조회 시 처리

    @Override
    public UserDto createUser(UserDto userDto){
        UserEntity userEntity = userRepository.save(userDto.toEntity());
        return new UserDto(userEntity.getName(), userEntity.getAge(), userEntity.getUserId(), userEntity.getPassword());
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto){
        userRepository.findById(id).orElseThrow(()->new NoSuchElementException("해당 id의 user가 없습니다."));
        UserEntity userUpdateEntity = UserEntity.builder().id(id).name(userDto.getName()).age(userDto.getAge()).userId(userDto.getUserId()).password(userDto.getPassword()).build();
        //setter를 지양?, entity 레벨에 새로운 메서드로 넣어도 되는가?
        UserEntity returnEntity = userRepository.save(userUpdateEntity);
        return new UserDto(returnEntity.getName(), returnEntity.getAge(), returnEntity.getUserId(), returnEntity.getPassword());

    }
    @Override
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
