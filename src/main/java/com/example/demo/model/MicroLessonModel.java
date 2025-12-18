package com.example.demo.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class MicroLessonModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer durationMinutes;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private String difficulty;

    private String tags;

    private LocalDate publishDate;

    public MicroLessonModel() {}

    public MicroLessonModel(Course course, String title, Integer durationMinutes,ContentType contentType, Difficulty difficulty,String tags, LocalDate publishDate) {
        this.course = course;
        this.title = title;
        this.durationMinutes = durationMinutes;
        this.contentType = contentType;
        this.difficulty = difficulty;
        this.tags = tags;
        this.publishDate = publishDate;
    }

    public Long getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        if (durationMinutes == null || durationMinutes <= 0 || durationMinutes > 15) {
            throw new IllegalArgumentException(
                "Duration must be between 1 and 15 minutes"
            );
        }
        this.durationMinutes = durationMinutes;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
}
