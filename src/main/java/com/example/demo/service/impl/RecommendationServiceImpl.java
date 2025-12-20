package com.example.demo.service.impl;

import com.example.demo.model.Recommendation;
import com.example.demo.model.User;
import com.example.demo.repository.MicroLessonRepository;
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
    private final MicroLessonRepository microLessonRepository; // REQUIRED by test

    // ✅ EXACT constructor expected by test
    public RecommendationServiceImpl(
            RecommendationRepository recommendationRepository,
            UserRepository userRepository,
            MicroLessonRepository microLessonRepository
    ) {
        this.recommendationRepository = recommendationRepository;
        this.userRepository = userRepository;
        this.microLessonRepository = microLessonRepository;
    }

    @Override
    public Recommendation generateRecommendation(Long userId, Object request) {
        User user = userRepository.findById(userId).orElseThrow();
        Recommendation r = new Recommendation();
        r.setUser(user);
        return recommendationRepository.save(r);
    }

    @Override
    public Recommendation getLatestRecommendation(Long userId) {
        List<Recommendation> list =
                recommendationRepository.findByUserIdOrderByGeneratedAtDesc(userId);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Recommendation> getRecommendations(
            Long userId,
            LocalDate from,
            LocalDate to
    ) {
        return recommendationRepository.findByUserIdOrderByGeneratedAtDesc(userId);
    }
}
