package com.backend.meditrack.service;

import com.backend.meditrack.dto.LoginRequest;
import com.backend.meditrack.dto.RegisterRequest;
import com.backend.meditrack.entity.User;
import com.backend.meditrack.exception.UserAlreadyExistsException;
import com.backend.meditrack.repository.UserRepository;
import com.backend.meditrack.security.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtility jwtutil;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(RegisterRequest request) {

        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email already exists");
        }


        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole("USER");

        return userRepository.save(user);
    }
    //Login Method
    public String loginUser(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail()).
                orElseThrow(()-> new RuntimeException("Invalid Email."));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Password.");
        }
        return jwtutil.generateToken(user.getEmail());
    }

}
