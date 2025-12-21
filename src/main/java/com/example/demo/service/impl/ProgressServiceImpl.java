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

    public ProgressServiceImpl(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    @Override
    public Progress create(Progress progress) {
        return progressRepository.save(progress);
    }

    @Override
    public Progress get(Long id) {
        return progressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Progress not found"));
    }

    @Override
    public List<Progress> getAll() {
        return progressRepository.findAll();
    }
}
