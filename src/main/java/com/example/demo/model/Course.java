package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private User instructor;

    private String category;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "course")
    private List<MicroLesson> lessons;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
