package com.dhikrplus.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dhikrs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dhikr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String arabicText;

    @Column(columnDefinition = "TEXT")
    private String transliteration;

    @Column(columnDefinition = "TEXT")
    private String meaning;

    @Column(name = "default_target")
    private Integer defaultTarget;

    @Column(columnDefinition = "TEXT")
    private String reference;
}
