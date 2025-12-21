package com.example.demo.service;

import com.example.demo.model.Course;

import java.util.List;

public interface CourseService {

    Course createCourse(Long instructorId, Course course);

    Course getCourseById(Long courseId);

    Course updateCourse(Long courseId, Course course);

    List<Course> getCoursesByInstructor(Long instructorId);
}
