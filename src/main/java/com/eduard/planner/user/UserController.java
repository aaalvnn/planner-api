package com.eduard.planner.user;

import com.eduard.planner.entity.User;
import com.eduard.planner.user.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @PostMapping()
    public User createUser(@RequestParam String username, @RequestParam String email) {
        return this.userService.createUser(username, email);
    }
}
