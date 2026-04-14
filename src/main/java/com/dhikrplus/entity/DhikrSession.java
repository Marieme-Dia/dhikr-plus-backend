package com.dhikrplus.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dhikr_sessions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DhikrSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dhikr_id", nullable = false)
    private Dhikr dhikr;

    @Column(nullable = false)
    private Integer count;

    @Column(name = "target")
    private Integer target;

    @Column(name = "completed")
    @Builder.Default
    private Boolean completed = false;

    @Column(name = "session_date")
    private LocalDateTime sessionDate;

    @PrePersist
    protected void onCreate() {
        sessionDate = LocalDateTime.now();
    }
}
