package com.example.demo.controller;

import com.example.demo.model.MicroLesson;
import com.example.demo.service.LessonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    public MicroLesson createLesson(@RequestBody MicroLesson lesson) {
        return lessonService.createLesson(lesson);
    }

    @PutMapping("/{id}")
    public MicroLesson updateLesson(@PathVariable Long id,
                                    @RequestBody MicroLesson lesson) {
        return lessonService.updateLesson(id, lesson);
    }

    @GetMapping("/{id}")
    public MicroLesson getLesson(@PathVariable Long id) {
        return lessonService.getLesson(id);
    }

    @GetMapping
    public List<MicroLesson> getAllLessons() {
        return lessonService.getAllLessons();
    }
}
