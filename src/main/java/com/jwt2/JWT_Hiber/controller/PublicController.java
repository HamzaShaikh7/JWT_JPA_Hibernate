package com.jwt2.JWT_Hiber.controller;


import com.jwt2.JWT_Hiber.entry.User;
import com.jwt2.JWT_Hiber.service.UserDetailsServiceIMPL;
import com.jwt2.JWT_Hiber.service.UserService;
import com.jwt2.JWT_Hiber.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {

    private static final String HEALTH_CHECK_SUCCESS = "OK";


    @Autowired
    private UserService userService;



    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceIMPL userDetailsServiceIMPL;
    @Autowired
    private JwtUtil jwtUtil;




    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>(HEALTH_CHECK_SUCCESS, HttpStatus.OK);
    }


    @PostMapping("/signup")
    public ResponseEntity<?> createNewAccount(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.createNewAccount(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
            UserDetails userDetails = userDetailsServiceIMPL.loadUserByUsername(user.getName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);


        } catch (Exception e) {
            log.error("Exception occurred while createAuthenticationToken ", e);
            return new ResponseEntity<>("Incorrect username and password", HttpStatus.BAD_REQUEST);

        }
    }


}