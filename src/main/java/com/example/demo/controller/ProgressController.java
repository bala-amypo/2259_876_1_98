package com.example.demo.controller;

import com.example.demo.model.Progress;
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

    @PostMapping
    public Progress createProgress(@RequestBody Progress progress) {
        return progressService.createProgress(progress);
    }

    @PutMapping("/{id}")
    public Progress updateProgress(@PathVariable Long id,
                                   @RequestBody Progress progress) {
        return progressService.updateProgress(id, progress);
    }

    @GetMapping("/{id}")
    public Progress getProgress(@PathVariable Long id) {
        return progressService.getProgress(id);
    }

    @GetMapping
    public List<Progress> getAllProgress() {
        return progressService.getAllProgress();
    }
}
