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
    @JoinColumn(name = "lesson_id", nullable = false)
    private MicroLesson microLesson;

    private String status;
    private Integer progressPercent;
    private BigDecimal score;
    private LocalDateTime lastAccessedAt;

    @PrePersist
    public void prePersist() {
        this.lastAccessedAt = LocalDateTime.now();
    }
}
