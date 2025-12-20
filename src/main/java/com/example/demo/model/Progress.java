package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private MicroLesson microLesson;

    private String status;

    private BigDecimal progressPercent;

    private LocalDateTime lastAccessedAt;

    private BigDecimal score;

    // ---------- JPA lifecycle ----------
    @PrePersist
    public void prePersist() {
        this.lastAccessedAt = LocalDateTime.now();
    }

    // ---------- Getters ----------
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public MicroLesson getMicroLesson() {
        return microLesson;
    }

    public String getStatus() {
        return status;
    }

    public BigDecimal getProgressPercent() {
        return progressPercent;
    }

    public LocalDateTime getLastAccessedAt() {
        return lastAccessedAt;
    }

    public BigDecimal getScore() {
        return score;
    }

    // ---------- Setters ----------
    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMicroLesson(MicroLesson microLesson) {
        this.microLesson = microLesson;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // REQUIRED for test cases (BigDecimal)
    public void setProgressPercent(BigDecimal progressPercent) {
        this.progressPercent = progressPercent;
    }

    // Optional overloads (safe)
    public void setProgressPercent(int value) {
        this.progressPercent = BigDecimal.valueOf(value);
    }

    public void setProgressPercent(double value) {
        this.progressPercent = BigDecimal.valueOf(value);
    }

    // REQUIRED for test cases (BigDecimal)
    public void setScore(BigDecimal score) {
        this.score = score;
    }

    // Optional overloads (safe)
    public void setScore(int value) {
        this.score = BigDecimal.valueOf(value);
    }

    public void setScore(double value) {
        this.score = BigDecimal.valueOf(value);
    }
}
