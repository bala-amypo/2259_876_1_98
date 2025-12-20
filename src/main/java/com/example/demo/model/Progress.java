package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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
    private User user;

    @ManyToOne
    private MicroLesson microLesson;

    private String status;

    // IMPORTANT: BigDecimal (as per test cases)
    private BigDecimal progressPercent;

    private LocalDateTime lastAccessedAt;

    private BigDecimal score;

    @PrePersist
    public void prePersist() {
        this.lastAccessedAt = LocalDateTime.now();
    }

    /* ===============================
       TEST-CASE COMPATIBLE SETTERS
       =============================== */

    // Allows: progress.setProgressPercent(50)
    public void setProgressPercent(int value) {
        this.progressPercent = BigDecimal.valueOf(value);
    }

    // Allows: progress.setProgressPercent(50.0)
    public void setProgressPercent(double value) {
        this.progressPercent = BigDecimal.valueOf(value);
    }

    // Allows: progress.setScore(10)
    public void setScore(int value) {
        this.score = BigDecimal.valueOf(value);
    }

    // Allows: progress.setScore(10.5)
    public void setScore(double value) {
        this.score = BigDecimal.valueOf(value);
    }
}
