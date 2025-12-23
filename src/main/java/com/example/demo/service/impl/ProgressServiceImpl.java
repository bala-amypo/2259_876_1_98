package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.MicroLesson;
import com.example.demo.model.Progress;
import com.example.demo.model.User;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.repository.ProgressRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ProgressService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProgressServiceImpl implements ProgressService {

    private final ProgressRepository progressRepository;
    private final UserRepository userRepository;
    private final MicroLessonRepository microLessonRepository;

    @Override
    public Progress recordProgress(Long userId, Long lessonId, Progress progress) {

        if (progress == null) {
            throw new IllegalArgumentException("Progress cannot be null");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        MicroLesson lesson = microLessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found"));

        Progress existingProgress = progressRepository
                .findByUserIdAndMicroLessonId(userId, lessonId)
                .orElse(null);

        if (progress.getProgressPercent() < 0 || progress.getProgressPercent() > 100) {
            throw new IllegalArgumentException("Invalid progress percent");
        }

        if ("COMPLETED".equals(progress.getStatus())
                && progress.getProgressPercent() != 100) {
            throw new IllegalArgumentException("Completed progress must be 100%");
        }

        if (existingProgress == null) {
            progress.setUser(user);
            progress.setMicroLesson(lesson);
            return progressRepository.save(progress);
        } else {
            existingProgress.setStatus(progress.getStatus());
            existingProgress.setProgressPercent(progress.getProgressPercent());
            existingProgress.setScore(progress.getScore());
            return progressRepository.save(existingProgress);
        }
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
