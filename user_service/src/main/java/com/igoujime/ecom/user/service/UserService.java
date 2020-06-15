package com.igoujime.ecom.user.service;

import com.igoujime.ecom.user.entity.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User findOne(Integer id);

    User findByUsernameAndPassword(String username, String password);

    List<User> findAll();

    void delete(Integer id);

}
