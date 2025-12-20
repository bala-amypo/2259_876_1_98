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
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private LocalDateTime generatedAt;

    private String recommendedLessonIds;

    private String basisSnapshot;

    // IMPORTANT: BigDecimal (as per test cases)
    private BigDecimal confidenceScore;

    @PrePersist
    public void prePersist() {
        this.generatedAt = LocalDateTime.now();
    }

    /* ===============================
       TEST-CASE COMPATIBLE SETTERS
       =============================== */

    // Allows: recommendation.setConfidenceScore(90)
    public void setConfidenceScore(int value) {
        this.confidenceScore = BigDecimal.valueOf(value);
    }

    // Allows: recommendation.setConfidenceScore(0.85)
    public void setConfidenceScore(double value) {
        this.confidenceScore = BigDecimal.valueOf(value);
    }
}
