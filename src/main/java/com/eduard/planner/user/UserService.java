package com.eduard.planner.user;

import com.eduard.planner.entity.User;
import com.eduard.planner.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(String username, String email) {
        User user = new User(username, email);
        return userRepository.save(user);
    }
}