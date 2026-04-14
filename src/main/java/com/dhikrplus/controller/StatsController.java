package com.dhikrplus.controller;

import com.dhikrplus.dto.StatsResponse;
import com.dhikrplus.entity.*;
import com.dhikrplus.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.*;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    @Autowired DhikrSessionRepository sessionRepository;
    @Autowired UserProgressRepository progressRepository;
    @Autowired UserRepository userRepository;
    @Autowired LessonRepository lessonRepository;

    @GetMapping
    public ResponseEntity<StatsResponse> getMyStats(Authentication auth) {
        User user = userRepository.findByUsername(auth.getName()).orElseThrow();
        Long userId = user.getId();

        Long totalDhikrs = sessionRepository.sumCountByUserId(userId);
        Long completedSessions = sessionRepository.countCompletedByUserId(userId);
        Long completedLessons = progressRepository.countByUserIdAndCompletedTrue(userId);

        // Count learned alphabet letters
        List<UserProgress> progress = progressRepository.findByUserId(userId);
        long learnedLetters = progress.stream()
            .filter(p -> p.getCompleted() && "alphabet".equals(p.getLesson().getLessonType()))
            .count();

        // Weekly stats (last 7 days)
        List<StatsResponse.DayStats> weeklyStats = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (int i = 6; i >= 0; i--) {
            LocalDateTime day = now.minusDays(i);
            LocalDateTime start = day.toLocalDate().atStartOfDay();
            LocalDateTime end = start.plusDays(1);
            List<DhikrSession> sessions = sessionRepository.findByUserIdAndSessionDateBetween(userId, start, end);
            long count = sessions.stream().mapToLong(DhikrSession::getCount).sum();
            String dayName = day.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.FRENCH);
            weeklyStats.add(StatsResponse.DayStats.builder().day(dayName).count(count).build());
        }

        StatsResponse stats = StatsResponse.builder()
            .totalDhikrs(totalDhikrs != null ? totalDhikrs : 0)
            .completedSessions(completedSessions)
            .learnedLetters(learnedLetters)
            .completedLessons(completedLessons)
            .streakDays(user.getStreakDays())
            .weeklyStats(weeklyStats)
            .build();

        return ResponseEntity.ok(stats);
    }
}
