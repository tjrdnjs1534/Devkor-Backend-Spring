package com.devkor.study.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;s

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<UserDto> findAllUsers(){
        return userService.findAllUsers(); //entity를 dto로 바꾸기
    }

    @GetMapping("/{id}")
    public UserDto findUserById(@PathVariable("id") Long id){
        return userService.findUserById(id);
    }

    @PostMapping("")
    public UserDto createUser(@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }


    @PatchMapping("/{id}")
    public UserDto updateUser(@PathVariable("id") Long id , @RequestBody UserDto userDto){
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }

}