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
    private final UserRepository userRepository;
    private final MicroLessonRepository lessonRepository;

    public ProgressServiceImpl(
            ProgressRepository progressRepository,
            UserRepository userRepository,
            MicroLessonRepository lessonRepository
    ) {
        this.progressRepository = progressRepository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
    }

    @Override
    public Progress recordProgress(Long userId, Long lessonId, Progress progress) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        MicroLesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found"));

        int percent = progress.getProgressPercent();

        // ✅ int-safe validation
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Progress percent must be between 0 and 100");
        }

        if ("COMPLETED".equals(progress.getStatus()) && percent != 100) {
            throw new IllegalArgumentException("Completed status requires 100% progress");
        }

        Progress existing = progressRepository
                .findByUserIdAndMicroLessonId(userId, lessonId)
                .orElse(null);

        if (existing == null) {
            progress.setUser(user);
            progress.setMicroLesson(lesson);
            return progressRepository.save(progress);
        }

        existing.setStatus(progress.getStatus());
        existing.setProgressPercent(percent);
        existing.setScore(progress.getScore());

        return progressRepository.save(existing);
    }

    @Override
    public Progress getProgress(Long userId, Long lessonId) {
        return progressRepository.findByUserIdAndMicroLessonId(userId, lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Progress not found"));
    }

    @Override
    public List<Progress> getUserProgress(Long userId) {
        return progressRepository.findByUserIdOrderByLastAccessedAtDesc(userId);
    }
}
