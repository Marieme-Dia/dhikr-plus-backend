package com.dhikrplus.controller;

import com.dhikrplus.entity.Audio;
import com.dhikrplus.repository.AudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audios")
public class AudioController {

    @Autowired AudioRepository audioRepository;

    @GetMapping
    public List<Audio> getAllAudios(@RequestParam(required = false) String category) {
        if (category != null && !category.isBlank()) {
            return audioRepository.findByCategory(category);
        }
        return audioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Audio> getAudioById(@PathVariable Long id) {
        return audioRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
