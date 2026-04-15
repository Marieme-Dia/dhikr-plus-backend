package com.dhikrplus.controller;

import com.dhikrplus.dto.*;
import com.dhikrplus.entity.User;
import com.dhikrplus.repository.UserRepository;
import com.dhikrplus.security.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://dhikr-plus-frontend.vercel.app")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired AuthenticationManager authenticationManager;
    @Autowired UserRepository userRepository;
    @Autowired PasswordEncoder encoder;
    @Autowired JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        return ResponseEntity.ok(new AuthResponse(jwt, user.getId(), user.getUsername(), user.getEmail(), user.getDisplayName()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            return ResponseEntity.badRequest().body("Nom d'utilisateur déjà pris");
        if (userRepository.existsByEmail(request.getEmail()))
            return ResponseEntity.badRequest().body("Email déjà utilisé");

        User user = User.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .password(encoder.encode(request.getPassword()))
            .displayName(request.getDisplayName() != null ? request.getDisplayName() : request.getUsername())
            .build();
        userRepository.save(user);
        return ResponseEntity.ok("Inscription réussie !");
    }
}
