package com.example.demo.service.impl;

import com.example.demo.model.MicroLesson;
import com.example.demo.model.Progress;
import com.example.demo.model.User;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.repository.ProgressRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ProgressService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgressServiceImpl implements ProgressService {

    private final ProgressRepository progressRepository;
    private final UserRepository userRepository;
    private final MicroLessonRepository microLessonRepository;

    public ProgressServiceImpl(ProgressRepository progressRepository,
                               UserRepository userRepository,
                               MicroLessonRepository microLessonRepository) {
        this.progressRepository = progressRepository;
        this.userRepository = userRepository;
        this.microLessonRepository = microLessonRepository;
    }

    // ✅ REQUIRED BY INTERFACE
    @Override
    public Progress getProgress(Long userId, Long lessonId) {
        return progressRepository.findByUserIdAndMicroLessonId(userId, lessonId)
                .orElseThrow(() -> new RuntimeException("Progress not found"));
    }

    // ✅ CREATE / UPDATE PROGRESS (FK SAFE)
    @Override
    public Progress recordProgress(Long userId, Long lessonId, Progress incoming) {

        if (incoming == null) {
            throw new RuntimeException("Progress data required");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        MicroLesson lesson = microLessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        Optional<Progress> existingOpt =
                progressRepository.findByUserIdAndMicroLessonId(userId, lessonId);

        Progress progress;

        if (existingOpt.isPresent()) {
            progress = existingOpt.get();
        } else {
            progress = new Progress();
            progress.setUser(user);
            progress.setMicroLesson(lesson); // 🔥 FK FIX
        }

        progress.setStatus(incoming.getStatus());
        progress.setProgressPercent(incoming.getProgressPercent());
        progress.setScore(incoming.getScore());

        return progressRepository.save(progress);
    }

    // ✅ REQUIRED BY INTERFACE
    @Override
    public List<Progress> getUserProgress(Long userId) {
        return progressRepository.findByUserIdOrderByLastAccessedAtDesc(userId);
    }
}
