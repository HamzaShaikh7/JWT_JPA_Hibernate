package com.jwt2.JWT_Hiber.service;


import com.jwt2.JWT_Hiber.entry.User;
import com.jwt2.JWT_Hiber.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;


@Service
public class UserService
{

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Autowired
    private UserRepository userRepository;

    public String createNewAccount(User user) {

        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setQuantity(20);
        userRepository.save(user);
        return "Account created.";
    }



    public List<User> getAllData(String name) {

        return Arrays.asList(userRepository.findByName(name));

    }
}
