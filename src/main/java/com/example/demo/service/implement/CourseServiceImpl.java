package com.example.demo.service.implement;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;

public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;
    @Override
    public Course create(Course course){
        return courseRepository.save(course);
    }
}
