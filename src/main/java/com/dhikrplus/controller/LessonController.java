package com.dhikrplus.controller;

import com.dhikrplus.entity.*;
import com.dhikrplus.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    @Autowired LessonRepository lessonRepository;
    @Autowired UserProgressRepository progressRepository;
    @Autowired UserRepository userRepository;

    @GetMapping
    public List<Lesson> getAllLessons(@RequestParam(required = false) String type) {
        if (type != null && !type.isBlank()) {
            return lessonRepository.findByLessonTypeOrderByOrderIndex(type);
        }
        return lessonRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getLesson(@PathVariable Long id) {
        return lessonRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<?> completeLesson(@PathVariable Long id, Authentication auth) {
        User user = userRepository.findByUsername(auth.getName()).orElseThrow();
        Lesson lesson = lessonRepository.findById(id).orElseThrow();

        UserProgress progress = progressRepository
            .findByUserIdAndLessonId(user.getId(), id)
            .orElse(UserProgress.builder().user(user).lesson(lesson).build());

        progress.setCompleted(true);
        progress.setCompletedAt(LocalDateTime.now());
        progressRepository.save(progress);
        return ResponseEntity.ok("Leçon complétée !");
    }

    @GetMapping("/my-progress")
    public List<UserProgress> getMyProgress(Authentication auth) {
        User user = userRepository.findByUsername(auth.getName()).orElseThrow();
        return progressRepository.findByUserId(user.getId());
    }
}
