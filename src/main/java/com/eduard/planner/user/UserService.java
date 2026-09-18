package com.eduard.planner.user;

import com.eduard.planner.entity.User;
import com.eduard.planner.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList()
        );
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDto.UserResponse createUser(UserDto.RegisterRequest body) {
        String hashedPass = this.passwordEncoder.encode(body.password());
        User user = userRepository.save(new User(body.username(), body.email(), hashedPass));
        return new UserDto.UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }
}