package com.eduard.planner.auth;

public record LoginRequest(
        String email,
        String password
) {}