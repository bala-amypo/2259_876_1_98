package com.example.demo.controller;

import com.example.demo.entity.Progress;
import com.example.demo.service.ProgressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/progress")
public class ProgressController {

    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @PostMapping("/{lessonId}")
    public Progress recordProgress(
            @RequestParam Long userId,
            @PathVariable Long lessonId,
            @RequestBody Progress progress
    ) {
        return progressService.recordProgress(userId, lessonId, progress);
    }

    @GetMapping("/lesson/{lessonId}")
    public Progress getProgress(
            @RequestParam Long userId,
            @PathVariable Long lessonId
    ) {
        return progressService.getProgress(userId, lessonId);
    }

    @GetMapping("/user/{userId}")
    public List<Progress> getUserProgress(@PathVariable Long userId) {
        return progressService.getUserProgress(userId);
    }
}
