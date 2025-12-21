package com.example.demo.service;

import com.example.demo.model.MicroLesson;

import java.util.List;

public interface LessonService {

    MicroLesson createLesson(MicroLesson lesson);

    MicroLesson updateLesson(Long id, MicroLesson lesson);

    MicroLesson getLesson(Long id);

    List<MicroLesson> getAllLessons();
}
