package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Course;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CourseService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    public Course createCourse(Course course, Long instructorId) {

        User instructor = userRepository.findById(instructorId).orElseThrow(() ->new ResourceNotFoundException("Instructor not found"));

        if (!instructor.getRole().equals("INSTRUCTOR") && !instructor.getRole().equals("ADMIN")) {
            throw new RuntimeException("User is not allowed to create courses");
        }

        if (courseRepository.existsByTitleAndInstructorId(course.getTitle(), instructorId)) {
            throw new RuntimeException("Course with this title already exists for this instructor");
        }

        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long courseId, Course course) {
        Course existingCourse = courseRepository.findById(courseId).orElseThrow(() ->new ResourceNotFoundException("Course not found"));

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
        return courseRepository.findById(courseId).orElseThrow(() ->new ResourceNotFoundException("Course not found"));
    }
}
