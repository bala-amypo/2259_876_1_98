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

    @Override
    public Progress recordProgress(Long userId, Long lessonId, Progress incoming) {

        // 🔹 1. Validate input
        if (incoming == null) {
            throw new RuntimeException("Progress data required");
        }

        // 🔹 2. Fetch User (FK safety)
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔹 3. Fetch MicroLesson (FK safety)
        MicroLesson lesson = microLessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        // 🔹 4. Check if progress already exists
        Optional<Progress> existingOpt =
                progressRepository.findByUserIdAndMicroLessonId(userId, lessonId);

        Progress progress;

        if (existingOpt.isPresent()) {
            // UPDATE existing progress
            progress = existingOpt.get();
        } else {
            // CREATE new progress
            progress = new Progress();
            progress.setUser(user);
            progress.setMicroLesson(lesson); // 🔥 REQUIRED for FK
        }

        // 🔹 5. Update progress fields
        progress.setStatus(incoming.getStatus());
        progress.setProgressPercent(incoming.getProgressPercent());
        progress.setScore(incoming.getScore());

        // 🔹 6. Save
        return progressRepository.save(progress);
    }

    @Override
    public List<Progress> getUserProgress(Long userId) {
        return progressRepository.findByUserIdOrderByLastAccessedAtDesc(userId);
    }
}
