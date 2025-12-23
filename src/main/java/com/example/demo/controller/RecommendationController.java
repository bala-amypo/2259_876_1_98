package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.RecommendationRequest;
import com.example.demo.model.Recommendation;
import com.example.demo.service.RecommendationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @PostMapping("/generate")
    public Recommendation generateRecommendation(
            @RequestParam Long userId,
            @RequestBody RecommendationRequest request) {

        return recommendationService.generateRecommendation(userId, request);
    }

    @GetMapping("/latest")
    public Recommendation getLatestRecommendation(
            @RequestParam Long userId) {

        return recommendationService.getLatestRecommendation(userId);
    }

    @GetMapping("/user/{userId}")
    public List<Recommendation> getRecommendations(
            @PathVariable Long userId,
            @RequestParam LocalDate from,
            @RequestParam LocalDate to) {

        return recommendationService.getRecommendations(userId, from, to);
    }
}
