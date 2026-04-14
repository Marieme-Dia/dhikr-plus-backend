package com.dhikrplus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private String displayName;

    public AuthResponse(String token, Long id, String username, String email, String displayName) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.displayName = displayName;
    }
}
