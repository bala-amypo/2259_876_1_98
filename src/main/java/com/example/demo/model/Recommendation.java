package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "recommendations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime generatedAt;

    @Column(nullable = false)
    private String recommendedLessonIds; // comma-separated ids

    @Column
    private String basisSnapshot;

    @Column(nullable = false, precision = 2, scale = 1)
    private BigDecimal confidenceScore; // 0.0 – 1.0

    @PrePersist
    public void prePersist() {
        this.generatedAt = LocalDateTime.now();
    }
}
