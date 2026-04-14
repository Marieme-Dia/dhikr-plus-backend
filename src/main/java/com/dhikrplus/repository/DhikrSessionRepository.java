package com.dhikrplus.repository;

import com.dhikrplus.entity.DhikrSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DhikrSessionRepository extends JpaRepository<DhikrSession, Long> {
    List<DhikrSession> findByUserId(Long userId);
    List<DhikrSession> findByUserIdAndSessionDateBetween(Long userId, LocalDateTime start, LocalDateTime end);

    @Query("SELECT COALESCE(SUM(s.count), 0) FROM DhikrSession s WHERE s.user.id = :userId")
    Long sumCountByUserId(Long userId);

    @Query("SELECT COUNT(s) FROM DhikrSession s WHERE s.user.id = :userId AND s.completed = true")
    Long countCompletedByUserId(Long userId);
}
