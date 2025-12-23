package com.example.demo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "recommendations")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDateTime generatedAt;

    @Column(length = 1000)
    private String recommendedLessonIds; // comma-separated ids

    @Column(length = 2000)
    private String basisSnapshot;

    @Column(nullable = false)
    private BigDecimal confidenceScore; // 0.0 – 1.0

    @PrePersist
    public void onCreate() {
        this.generatedAt = LocalDateTime.now();
    }
}
