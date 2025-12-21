package com.example.demo.controller;

import com.example.demo.model.Recommendation;
import com.example.demo.service.RecommendationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping
    public Recommendation createRecommendation(@RequestBody Recommendation recommendation) {
        return recommendationService.createRecommendation(recommendation);
    }

    @PutMapping("/{id}")
    public Recommendation updateRecommendation(@PathVariable Long id,
                                               @RequestBody Recommendation recommendation) {
        return recommendationService.updateRecommendation(id, recommendation);
    }

    @GetMapping("/{id}")
    public Recommendation getRecommendation(@PathVariable Long id) {
        return recommendationService.getRecommendation(id);
    }

    @GetMapping
    public List<Recommendation> getAllRecommendations() {
        return recommendationService.getAllRecommendations();
    }
}
