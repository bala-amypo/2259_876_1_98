package com.example.demon.service.implement;

import org.springframework.stereotype.Service;

import com.example.demon.model.User;
import com.example.demon.repository.UserRepository;
import com.example.demon.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    @Override
    public User register(User user){
        return userRepository.save(user);
    }
}
