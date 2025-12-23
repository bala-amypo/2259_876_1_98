package com.example.demo.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.*;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByTitleAndInstructorId(String title, Long instructorId);
    List<Course> findByInstructorId(Long instructorId);
}

public interface MicroLessonRepository extends JpaRepository<MicroLesson, Long> {
    List<MicroLesson> findByTagsContainingAndDifficultyAndContentType(
        String tags, String difficulty, String contentType);
}

public interface ProgressRepository extends JpaRepository<Progress, Long> {
    Optional<Progress> findByUserIdAndMicroLessonId(Long userId, Long lessonId);
    List<Progress> findByUserIdOrderByLastAccessedAtDesc(Long userId);
}

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findByUserIdOrderByGeneratedAtDesc(Long userId);
}
