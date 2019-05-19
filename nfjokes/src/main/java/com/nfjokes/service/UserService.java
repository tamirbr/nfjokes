package com.nfjokes.service;

import com.nfjokes.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User findByEmail(String email);
    void save(User user);
    void update(User user);
    void delete(Long id);
}
