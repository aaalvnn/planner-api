package com.eduard.planner.user;

public class UserDto {

    public record RegisterRequest(
            String username,
            String email,
            String password
    ) {}

    public record LoginRequest(
            String email,
            String password
    ) {}

    public record UserResponse(
            String id,
            String username,
            String email
    ) {}
}
