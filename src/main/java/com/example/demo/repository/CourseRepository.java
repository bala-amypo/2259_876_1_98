package com.example.demo.repository;

import java.util.*;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.*;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByTitleAndInstructorId(String title, Long instructorId);
    List<Course> findByInstructorId(Long instructorId);
}
