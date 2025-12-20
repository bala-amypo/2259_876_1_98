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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDateTime generatedAt;

    @Column(length = 1000)
    private String recommendedLessonIds;

    @Column(length = 2000)
    private String basisSnapshot;

    private BigDecimal confidenceScore;

    @prePersist
    public void onCreate() {
        this.generatedAt = LocalDateTime.now();
    }
}
