package com.example.demon.model;

import java.time.LocalDateTime;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

public class ProgressModel {
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id",nullable=false)
    private UserModel user;
    private MicroLesson microLesson;
    private String status;
    private int progressPercent;
    private LocalDateTime lastAccessedAt;
    @PrePersist
    public void prePersist() {
        this.lastAccessedAt = LocalDateTime.now();
    }
    private Double score;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public UserModel getUser() {
        return user;
    }
    public void setUser(UserModel user) {
        this.user = user;
    }
    public MicroLesson getMicroLesson() {
        return microLesson;
    }
    public void setMicroLesson(MicroLesson microLesson) {
        this.microLesson = microLesson;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getProgressPercent() {
        return progressPercent;
    }
    public void setProgressPercent(int progressPercent) {
        this.progressPercent = progressPercent;
    }
    public LocalDateTime getLastAccessedAt() {
        return lastAccessedAt;
    }
    public void setLastAccessedAt(LocalDateTime lastAccessedAt) {
        this.lastAccessedAt = lastAccessedAt;
    }
    public Double getScore() {
        return score;
    }
    public void setScore(Double score) {
        this.score = score;
    }
    public ProgressModel(Long id, UserModel user, MicroLesson microLesson, String status, int progressPercent,
            LocalDateTime lastAccessedAt, Double score) {
        this.id = id;
        this.user = user;
        this.microLesson = microLesson;
        this.status = status;
        this.progressPercent = progressPercent;
        this.lastAccessedAt = lastAccessedAt;
        this.score = score;
    }
    public ProgressModel() {
    }
    

}
