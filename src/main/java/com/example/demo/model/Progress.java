package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private MicroLesson microLesson;

    private String status; // NOT_STARTED, IN_PROGRESS, COMPLETED

    private int progressPercent;   // ✅ CHANGED FROM BigDecimal → int

    private LocalDateTime lastAccessedAt;

    private int score;              // ✅ CHANGED FROM BigDecimal → int

    @PrePersist
    public void prePersist() {
        this.lastAccessedAt = LocalDateTime.now();
    }
}
