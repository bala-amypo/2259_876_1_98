package com.example.demo.service;

import com.example.demo.model.Course;

import java.util.List;

public interface CourseService {

    Course createCourse(Long instructorId, Course course);

    Course updateCourse(Long courseId, Course course);

    Course getCourseById(Long courseId);

    List<Course> getCoursesByInstructor(Long instructorId);
}
