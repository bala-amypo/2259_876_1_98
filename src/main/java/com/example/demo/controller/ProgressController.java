package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Progress;
import com.example.demo.service.ProgressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;

    @PostMapping("/{lessonId}")
    public Progress recordProgress(
            @RequestParam Long userId,
            @PathVariable Long lessonId,
            @RequestBody Progress progress) {

        return progressService.recordProgress(userId, lessonId, progress);
    }

    @GetMapping("/lesson/{lessonId}")
    public Progress getProgress(
            @RequestParam Long userId,
            @PathVariable Long lessonId) {

        return progressService.getProgress(userId, lessonId);
    }

    @GetMapping("/user/{userId}")
    public List<Progress> getUserProgress(
            @PathVariable Long userId) {

        return progressService.getUserProgress(userId);
    }
}
