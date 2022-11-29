package com.konkuk.toastfactory.rank.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RankingResDto {
    private final String name;
    private final double score;
}
