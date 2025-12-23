package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Course;
import com.example.demo.model.MicroLesson;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.service.LessonService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final MicroLessonRepository microLessonRepository;
    private final CourseRepository courseRepository;

    @Override
    public MicroLesson addLesson(Long courseId, MicroLesson lesson) {

        if (lesson == null) {
            throw new IllegalArgumentException("Lesson cannot be null");
        }

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        if (lesson.getDurationMinutes() == null || lesson.getDurationMinutes() <= 0) {
            throw new IllegalArgumentException("Invalid lesson duration");
        }


        lesson.setCourse(course);
        return microLessonRepository.save(lesson);
    }

    @Override
    public MicroLesson updateLesson(Long lessonId, MicroLesson lesson) {

        MicroLesson existingLesson = microLessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found"));

        existingLesson.setTitle(lesson.getTitle());
        existingLesson.setDurationMinutes(lesson.getDurationMinutes());
        existingLesson.setContentType(lesson.getContentType());
        existingLesson.setDifficulty(lesson.getDifficulty());
        existingLesson.setTags(lesson.getTags());
        existingLesson.setPublishDate(lesson.getPublishDate());

        return microLessonRepository.save(existingLesson);
    }

    @Override
    public List<MicroLesson> findLessonsByFilters(
            String tags,
            String difficulty,
            String contentType) {

        return microLessonRepository
                .findByFilters(
                        tags, difficulty, contentType);
    }

    @Override
    public MicroLesson getLesson(Long lessonId) {
        return microLessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found"));
    }
}
