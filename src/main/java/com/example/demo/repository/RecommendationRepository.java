package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Recommendation;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

    List<Recommendation> findByUserIdOrderByGeneratedAtDesc(Long userId);

    List<Recommendation> findByUserIdAndGeneratedAtBetween(
            Long userId, LocalDateTime start, LocalDateTime end);
}
