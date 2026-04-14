package com.dhikrplus.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lessons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "arabic_title")
    private String arabicTitle;

    @Column(name = "lesson_type")
    private String lessonType; // alphabet | sourate | vocab

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "level")
    private String level; // debutant | intermediaire | avance

    @Column(name = "order_index")
    private Integer orderIndex;

    @Column(name = "icon")
    private String icon;
}
