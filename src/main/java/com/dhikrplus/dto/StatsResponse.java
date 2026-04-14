package com.dhikrplus.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class StatsResponse {
    private Long totalDhikrs;
    private Long completedSessions;
    private Long learnedLetters;
    private Long completedLessons;
    private Integer streakDays;
    private List<DayStats> weeklyStats;

    @Data
    @Builder
    public static class DayStats {
        private String day;
        private Long count;
    }
}
