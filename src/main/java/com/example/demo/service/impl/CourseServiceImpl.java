package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Course;

import com.example.demo.repository.CourseRepository;

import com.example.demo.service.CourseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
  

   

    @Override
    public Course updateCourse(Long courseId, Course course) {

        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        existingCourse.setTitle(course.getTitle());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setCategory(course.getCategory());

        return courseRepository.save(existingCourse);
    }

    

    @Override
    public Course getCourse(Long courseId) {

        return courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }
}
