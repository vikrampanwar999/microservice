package com.igoujime.ecom.user.service.impl;

import com.igoujime.ecom.user.entity.User;
import com.igoujime.ecom.user.repository.UserRepository;
import com.igoujime.ecom.user.service.UserService;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        LOGGER.info("Save user: {}", user);
        User saved = userRepository.save(user);
        return saved;
    }

    @Override
    public User findOne(Integer id) {
        LOGGER.info("Find user, id: {}", id);
        return userRepository.findOne(id);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public List<User> findAll() {
        LOGGER.info("Find all users");
        Iterable<User> products = userRepository.findAll();
        return IteratorUtils.toList(products.iterator());
    }

    @Override
    public void delete(Integer id) {
        LOGGER.info("Delete user, id: {}", id);
        userRepository.delete(id);
    }

}
