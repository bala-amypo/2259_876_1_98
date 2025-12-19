package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecommendationRequest {

    private String difficulty;
    private String tags;
    private Integer maxItems;
}
