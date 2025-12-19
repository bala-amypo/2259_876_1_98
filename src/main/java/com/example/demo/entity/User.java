// package com.example.demo.entity;

// import java.time.LocalDateTime;
// import java.util.List;

// import jakarta.persistence.*;
// import lombok.*;

// @Entity
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class User {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false, length = 100)
//     private String fullName;

//     @Column(nullable = false, unique = true)
//     private String email;

//     @Column(nullable = false)
//     private String password; // BCrypt hashed

//     @Column(nullable = false)
//     private String role; // LEARNER, INSTRUCTOR, ADMIN

//     private String preferredLearningStyle;

//     private LocalDateTime createdAt;

//     // Relationships
//     @OneToMany(mappedBy = "instructor")
//     private List<Course> courses;

//     @OneToMany(mappedBy = "user")
//     private List<Progress> progressList;

//     @OneToMany(mappedBy = "user")
//     private List<R
