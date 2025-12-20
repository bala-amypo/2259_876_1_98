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
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private MicroLesson microLesson;

    private String status;

    @Column(nullable = false)
    private BigDecimal progressPercent;

    private BigDecimal score;

    private LocalDateTime lastAccessedAt;

    @PrePersist
    public void prePersist() {
        this.lastAccessedAt = LocalDateTime.now();
        if (this.progressPercent == null) {
            this.progressPercent = BigDecimal.ZERO;
        }
        if (this.score == null) {
            this.score = BigDecimal.ZERO;
        }
    }
}
