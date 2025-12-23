package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Course;
import com.example.demo.service.CourseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public Course createCourse(
            @RequestParam Long instructorId,
            @RequestBody Course course) {

        return courseService.createCourse(course, instructorId);
    }

    @PutMapping("/{courseId}")
    public Course updateCourse(
            @PathVariable Long courseId,
            @RequestBody Course course) {

        return courseService.updateCourse(courseId, course);
    }

    @GetMapping("/instructor/{instructorId}")
    public List<Course> listCoursesByInstructor(
            @PathVariable Long instructorId) {

        return courseService.listCoursesByInstructor(instructorId);
    }

    @GetMapping("/{courseId}")
    public Course getCourse(
            @PathVariable Long courseId) {

        return courseService.getCourse(courseId);
    }
}
