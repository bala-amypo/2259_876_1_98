package com.example.demo.controller;

import com.example.demo.model.Recommendation;
import com.example.demo.service.RecommendationService;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping("/generate")
    public Recommendation generate(@RequestParam Long userId,
                                   @RequestBody RecommendationRequest request) {
        return recommendationService.generateRecommendation(userId, request);
    }

    @GetMapping("/latest")
    public Recommendation getLatest(@RequestParam Long userId) {
        return recommendationService.getLatestRecommendation(userId);
    }

    @GetMapping("/user/{userId}")
    public List<Recommendation> getUserRecommendations(@PathVariable Long userId,
                                                       @RequestParam LocalDate from,
                                                       @RequestParam LocalDate to) {
        return recommendationService.getRecommendations(userId, from, to);
    }
}
