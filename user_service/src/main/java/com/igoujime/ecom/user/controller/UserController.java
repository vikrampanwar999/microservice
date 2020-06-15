package com.igoujime.ecom.user.controller;

import com.igoujime.ecom.user.dto.UserDto;
import com.igoujime.ecom.user.entity.User;
import com.igoujime.ecom.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/{id}")
    public User getOne(@PathVariable Integer id) {
        return userService.findOne(id);
    }

    @PostMapping(value = "/")
    public User create(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping(value = "/login")
    public boolean login(@RequestBody UserDto userDto) {
        User user = userService.findByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
        if (user != null) {
            return true;
        }
        return false;
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

}
