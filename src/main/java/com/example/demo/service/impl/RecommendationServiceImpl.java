package com.example.demo.service.impl;

import com.example.demo.model.Recommendation;
import com.example.demo.model.User;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;

    // ✅ EXACT constructor required by test
    public RecommendationServiceImpl(
            RecommendationRepository recommendationRepository,
            UserRepository userRepository
    ) {
        this.recommendationRepository = recommendationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Recommendation generateRecommendation(Long userId, Object request) {
        User user = userRepository.findById(userId).orElseThrow();
        Recommendation recommendation = new Recommendation();
        recommendation.setUser(user);
        return recommendationRepository.save(recommendation);
    }

    @Override
    public Recommendation getLatestRecommendation(Long userId) {
        List<Recommendation> list =
                recommendationRepository.findByUserIdOrderByGeneratedAtDesc(userId);
        return list.isEmpty() ? null : list.get(0);
    }

    // ✅ THIS METHOD WAS MISSING (CAUSE OF ERROR)
    @Override
    public List<Recommendation> getRecommendations(
            Long userId,
            LocalDate from,
            LocalDate to
    ) {
        return recommendationRepository.findByUserIdOrderByGeneratedAtDesc(userId);
    }
}
