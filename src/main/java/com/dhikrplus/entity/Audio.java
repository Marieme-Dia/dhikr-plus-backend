package com.dhikrplus.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "audios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Audio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "arabic_title")
    private String arabicTitle;

    @Column(nullable = false)
    private String category; // coran | dua | dhikr

    @Column(name = "reciter")
    private String reciter;

    @Column(name = "duration")
    private String duration;

    @Column(name = "url", columnDefinition = "TEXT")
    private String url;

    @Column(name = "icon")
    private String icon;

    @Column(name = "surah_number")
    private Integer surahNumber;
}
