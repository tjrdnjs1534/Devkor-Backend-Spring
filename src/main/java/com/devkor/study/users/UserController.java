package com.devkor.study.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<UserDTO> findAllUsers(){
        return userService.findAllUsers(); //entity를 dto로 바꾸기
    }

    @GetMapping("/{id}")
    public UserDTO findUserById(@PathVariable("id") Long id){
        return userService.findUserById(id);
    }


    @PostMapping("")
    public UserDTO createUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }


    @PatchMapping("/{id}")
    public UserDTO updateUser(@PathVariable("id") Long id , @RequestBody UserDTO userDTO){
        return userService.updateUser(id, userDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }

}