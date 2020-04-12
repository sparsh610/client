package com.freelancing.servercode.service;

import java.util.List;

import com.freelancing.servercode.table.User;

public interface UserService
{
    User findByUsername(String username);

    List<User> findAllUsers();

    User saveUser(User user);
}