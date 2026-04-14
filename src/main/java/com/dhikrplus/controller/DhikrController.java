package com.dhikrplus.controller;

import com.dhikrplus.dto.DhikrSessionRequest;
import com.dhikrplus.entity.*;
import com.dhikrplus.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DhikrController {

    @Autowired DhikrRepository dhikrRepository;
    @Autowired DhikrSessionRepository sessionRepository;
    @Autowired UserRepository userRepository;

    // --- Dhikrs (public) ---
    @GetMapping("/dhikrs")
    public List<Dhikr> getAllDhikrs() {
        return dhikrRepository.findAll();
    }

    @GetMapping("/dhikrs/{id}")
    public ResponseEntity<Dhikr> getDhikrById(@PathVariable Long id) {
        return dhikrRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // --- Sessions (protected) ---
    @PostMapping("/sessions")
    public ResponseEntity<?> saveSession(@RequestBody DhikrSessionRequest req, Authentication auth) {
        User user = userRepository.findByUsername(auth.getName()).orElseThrow();
        Dhikr dhikr = dhikrRepository.findById(req.getDhikrId()).orElseThrow();
        DhikrSession session = DhikrSession.builder()
            .user(user).dhikr(dhikr)
            .count(req.getCount())
            .target(req.getTarget())
            .completed(req.getCompleted() != null ? req.getCompleted() : false)
            .build();
        return ResponseEntity.ok(sessionRepository.save(session));
    }

    @GetMapping("/sessions")
    public List<DhikrSession> getMySessions(Authentication auth) {
        User user = userRepository.findByUsername(auth.getName()).orElseThrow();
        return sessionRepository.findByUserId(user.getId());
    }
}
