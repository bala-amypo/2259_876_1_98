package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // CREATE
    @PostMapping
    public Course createCourse(
            @RequestParam Long instructorId,
            @RequestBody Course course
    ) {
        return courseService.createCourse(instructorId, course);
    }

    // READ by ID
    @GetMapping("/{courseId}")
    public Course getCourse(@PathVariable Long courseId) {
        return courseService.getCourseById(courseId);
    }

    // UPDATE
    @PutMapping("/{courseId}")
    public Course updateCourse(
            @PathVariable Long courseId,
            @RequestBody Course course
    ) {
        return courseService.updateCourse(courseId, course);
    }

    // READ by Instructor
    @GetMapping("/instructor/{instructorId}")
    public List<Course> getByInstructor(@PathVariable Long instructorId) {
        return courseService.getCoursesByInstructor(instructorId);
    }
}
