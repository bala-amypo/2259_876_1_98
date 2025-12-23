package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.MicroLesson;

public interface MicroLessonRepository extends JpaRepository<MicroLesson, Long> {

    List<MicroLesson> findByTagsContainingAndDifficultyAndContentType(
            String tags, String difficulty, String contentType);
}
