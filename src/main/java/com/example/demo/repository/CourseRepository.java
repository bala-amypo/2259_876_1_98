package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

    boolean existsByTitleAndInstructorId(String title, Long instructorId);

    List<Course> findByInstructorId(Long instructorId);
}
