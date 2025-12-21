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

    // ✅ CREATE COURSE (THIS FIXES SWAGGER BODY)
    @PostMapping
    public Course createCourse(
            @RequestParam Long instructorId,
            @RequestBody Course course   // ✅ THIS IS THE KEY
    ) {
        return courseService.createCourse(instructorId, course);
    }

    @GetMapping("/{courseId}")
    public Course getCourse(@PathVariable Long courseId) {
        return courseService.getCourse(courseId);
    }

    @PutMapping("/{courseId}")
    public Course updateCourse(
            @PathVariable Long courseId,
            @RequestBody Course course
    ) {
        return courseService.updateCourse(courseId, course);
    }

    @GetMapping("/instructor/{instructorId}")
    public List<Course> getCoursesByInstructor(@PathVariable Long instructorId) {
        return courseService.getCoursesByInstructor(instructorId);
    }
}
