package com.example.demo.service;

import com.example.demo.model.Recommendation;

import java.util.List;

public interface RecommendationService {

    Recommendation createRecommendation(Recommendation recommendation);

    Recommendation updateRecommendation(Long id, Recommendation recommendation);

    Recommendation getRecommendation(Long id);

    List<Recommendation> getAllRecommendations();
}
