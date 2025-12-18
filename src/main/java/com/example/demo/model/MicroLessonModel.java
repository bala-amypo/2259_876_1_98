package com.example.demo.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MicroLessonModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private CourseModel course;

    private String title;

    private Integer durationMinutes;

    private String contentType;

    private String difficulty;

    private String tags;

    private LocalDate publishDate;
}
