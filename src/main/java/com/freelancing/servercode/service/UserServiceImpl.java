package com.freelancing.servercode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freelancing.servercode.repository.UserRepository;
import com.freelancing.servercode.table.User;

@Service
@Transactional
public class UserServiceImpl
    implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username)
    {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<User> findAllUsers()
    {
        return userRepository.findAll();
    }
}
