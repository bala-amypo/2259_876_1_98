package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Course;
import com.example.demo.model.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CourseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    public Course createCourse(Course course, Long instructorId) {

        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }

        User instructor = userRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!"INSTRUCTOR".equals(instructor.getRole())
                && !"ADMIN".equals(instructor.getRole())) {
            throw new IllegalArgumentException("User is not allowed to create courses");
        }

        if (course.getTitle() == null || course.getTitle().isBlank()) {
            throw new IllegalArgumentException("Course title is required");
        }

        if (courseRepository.existsByTitleAndInstructorId(
                course.getTitle(), instructorId)) {
            throw new IllegalArgumentException("Course title already exists for instructor");
        }

        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long courseId, Course course) {

        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        existingCourse.setTitle(course.getTitle());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setCategory(course.getCategory());

        return courseRepository.save(existingCourse);
    }

    @Override
    public List<Course> listCoursesByInstructor(Long instructorId) {
        return courseRepository.findByInstructorId(instructorId);
    }

    @Override
    public Course getCourse(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }
}
