package com.example.demo.service.implement;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public User register(User user){
        return userRepository.save(user);
    }
    @Override
    public User login(User user){
        return userRepository.save(user);
    }
}
