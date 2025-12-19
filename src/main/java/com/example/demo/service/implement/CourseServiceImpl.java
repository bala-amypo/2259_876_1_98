package com.example.demo.service.implement;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Course;
import com.example.demo.model.User;
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

    public Course createCourse(Course course, Long instructorId) {
        User instructor = userRepository.findById(instructorId).orElse(null);
        if (instructor == null) return null;
        if (!"INSTRUCTOR".equals(instructor.getRole())
            && !"ADMIN".equals(instructor.getRole())) return null;
        if (courseRepository.existsByTitleAndInstructorId(course.getTitle(), instructorId))
            return null;

        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    public Course updateCourse(Long courseId, Course course) {
        Course existing = courseRepository.findById(courseId).orElse(null);
        if (existing == null) return null;

        existing.setTitle(course.getTitle());
        existing.setDescription(course.getDescription());
        existing.setCategory(course.getCategory());
        return courseRepository.save(existing);
    }

    public List<Course> listCoursesByInstructor(Long instructorId) {
        return courseRepository.findByInstructorId(instructorId);
    }

    public Course getCourse(Long courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }
}
