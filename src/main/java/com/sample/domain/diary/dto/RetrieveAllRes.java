package com.sample.domain.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class RetrieveAllRes {
    private Long id;
    private String title;
    private String content;
    private String code;
}
