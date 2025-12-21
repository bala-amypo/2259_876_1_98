package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.MicroLesson;
import com.example.demo.model.Progress;
import com.example.demo.model.User;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.repository.ProgressRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ProgressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressServiceImpl implements ProgressService {

    private final ProgressRepository progressRepository;
    private final MicroLessonRepository lessonRepository;

    public ProgressServiceImpl(ProgressRepository progressRepository,
                               MicroLessonRepository lessonRepository) {
        this.progressRepository = progressRepository;
        this.lessonRepository = lessonRepository;
    }

    @Override
    public Progress recordProgress(Long lessonId, Progress progress) {

        MicroLesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        progress.setMicroLesson(lesson);
        return progressRepository.save(progress);
    }

    @Override
    public Progress getProgress(Long progressId) {
        return progressRepository.findById(progressId)
                .orElseThrow(() -> new RuntimeException("Progress not found"));
    }

    @Override
    public List<Progress> getAllProgress() {
        return progressRepository.findAll();
    }
}
