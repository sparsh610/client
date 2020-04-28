package com.freelancing.servercode.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.freelancing.servercode.jwt.JwtTokenProvider;
import com.freelancing.servercode.model.JwtResponse;
import com.freelancing.servercode.model.LoginForm;
import com.freelancing.servercode.model.ResponseMessage;
import com.freelancing.servercode.model.RoleName;
import com.freelancing.servercode.model.SignUpForm;
import com.freelancing.servercode.repository.RoleRepository;
import com.freelancing.servercode.repository.UserRepository;
import com.freelancing.servercode.table.Role;
import com.freelancing.servercode.table.User;;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController
{
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;

    @PostMapping("api/user/registration")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpRequest)
    {
        if (userRepository.existsByUsername(signUpRequest.getUsername()))
        {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                                        HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail()))
        {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                                        HttpStatus.BAD_REQUEST);
        }
        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                             signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch (role)
            {
            case "admin":
                Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                roles.add(adminRole);
                break;
            case "pm":
                Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
                    .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                roles.add(pmRole);
                break;
            default:
                Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                roles.add(userRole);
            }
        });
        user.setRoles(roles);
        userRepository.save(user);
        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"),
                                    HttpStatus.OK);
    }

    @PostMapping("api/user/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginForm loginRequest)
    {
        Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                                                                  loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity
            .ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }
}
