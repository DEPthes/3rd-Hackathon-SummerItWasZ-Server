package com.sample.domain.diary.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateDiaryRes {
    private Long id;
    private String code;
}
