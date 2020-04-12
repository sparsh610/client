package com.freelancing.servercode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freelancing.servercode.service.UserService;

@RestController
public class AdminController
{
    @Autowired
    private UserService userService;

    @GetMapping("admin/allusers")
    public ResponseEntity<?> findAllUsers()
    {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }
}
