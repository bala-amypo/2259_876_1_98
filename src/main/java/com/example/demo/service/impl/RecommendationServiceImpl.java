package com.example.demo.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.RecommendationRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.MicroLesson;
import com.example.demo.model.Recommendation;
import com.example.demo.model.User;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RecommendationService;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;
    private final MicroLessonRepository microLessonRepository;

    @Override
    public Recommendation generateRecommendation(Long userId, RecommendationRequest params) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<MicroLesson> lessons = microLessonRepository
                .findByTagsContainingAndDifficultyAndContentType(
                        params.getTags(),
                        params.getDifficulty(),
                        params.getContentType()
                );

        String lessonIds = lessons.stream()
                .map(l -> String.valueOf(l.getId()))
                .collect(Collectors.joining(","));

        Recommendation recommendation = Recommendation.builder()
                .user(user)
                .recommendedLessonIds(lessonIds)
                .basisSnapshot("AUTO_GENERATED")
                .confidenceScore(BigDecimal.valueOf(0.8))
                .build();

        return recommendationRepository.save(recommendation);
    }

    @Override
    public Recommendation getLatestRecommendation(Long userId) {

        List<Recommendation> list =
                recommendationRepository.findByUserIdOrderByGeneratedAtDesc(userId);

        if (list.isEmpty()) {
            throw new ResourceNotFoundException("Recommendation not found");
        }

        return list.get(0);
    }

    @Override
    public List<Recommendation> getRecommendations(
            Long userId,
            LocalDate from,
            LocalDate to) {

        LocalDateTime start = from.atStartOfDay();
        LocalDateTime end = to.atTime(23, 59, 59);

        return recommendationRepository
                .findByUserIdAndGeneratedAtBetween(userId, start, end);
    }
}
