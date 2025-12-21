package com.example.demo.service;

import com.example.demo.dto.CourseRequest;
import com.example.demo.model.Course;

import java.util.List;

public interface CourseService {

    Course createCourse(Long instructorId, CourseRequest request);

    Course updateCourse(Long courseId, CourseRequest request);

    Course getCourse(Long courseId);

    List<Course> getCoursesByInstructor(Long instructorId);
}
