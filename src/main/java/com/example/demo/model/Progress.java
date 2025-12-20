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

    // ✅ Stored as BigDecimal
    private BigDecimal progressPercent;

    private LocalDateTime lastAccessedAt;

    private BigDecimal score;

    /* ---------- OVERLOADED SETTERS (THIS IS THE KEY) ---------- */

    public void setProgressPercent(int value) {
        this.progressPercent = BigDecimal.valueOf(value);
    }

    public void setScore(int value) {
        this.score = BigDecimal.valueOf(value);
    }

    /* ---------------------------------------------------------- */

    @PrePersist
    public void prePersist() {
        this.lastAccessedAt = LocalDateTime.now();
    }
}
