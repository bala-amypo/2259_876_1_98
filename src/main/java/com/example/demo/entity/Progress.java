package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "progress")
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
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "micro_lesson_id", nullable = false)
    private MicroLesson microLesson;

    // NOT_STARTED, IN_PROGRESS, COMPLETED
    private String status;

    private Integer progressPercent;

    private LocalDateTime lastAccessedAt;

    private BigDecimal score;

    @PrePersist
    public void onCreate() {
        this.lastAccessedAt = LocalDateTime.now();
    }
}
