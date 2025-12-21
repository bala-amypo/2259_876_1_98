package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.MicroLesson;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.service.LessonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    private final MicroLessonRepository lessonRepository;

    public LessonServiceImpl(MicroLessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public MicroLesson createLesson(MicroLesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public MicroLesson updateLesson(Long id, MicroLesson lesson) {
        MicroLesson existing = lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found"));

        existing.setTitle(lesson.getTitle());
        existing.setDurationMinutes(lesson.getDurationMinutes());
        existing.setContentType(lesson.getContentType());
        existing.setDifficulty(lesson.getDifficulty());
        existing.setTags(lesson.getTags());
        existing.setPublishDate(lesson.getPublishDate());

        return lessonRepository.save(existing);
    }

    @Override
    public MicroLesson getLesson(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found"));
    }

    @Override
    public List<MicroLesson> getAllLessons() {
        return lessonRepository.findAll();
    }
}
