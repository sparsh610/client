package com.freelancing.servercode.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.freelancing.servercode.jwt.JwtTokenProvider;
import com.freelancing.servercode.service.TestService;
import com.freelancing.servercode.service.UserService;
import com.freelancing.servercode.table.Role;
import com.freelancing.servercode.table.User;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController
{
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private TestService testService;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/registration")
    public void createNewUser(@RequestBody User user)
    {
        testService.saveUser(user);
    }

    @PostMapping("api/user/registration")
    public ResponseEntity<?> register(@RequestBody User user)
    {
        if (userService.findByUsername(user.getUsername()) != null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        user.setRole(Role.USER);
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("api/user/login")
    public ResponseEntity<?> login(Principal principal)
    {
        if (principal == null)
        {
            return ResponseEntity.ok(principal);
        }
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
        User user = userService.findByUsername(token.getName());
        user.setToken(jwtTokenProvider.generateToken(token));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
