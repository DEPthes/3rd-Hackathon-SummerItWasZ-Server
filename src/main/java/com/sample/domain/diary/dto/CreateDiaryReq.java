package com.sample.domain.diary.dto;

import com.sample.domain.diary.domain.DiaryFrame;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class CreateDiaryReq {
    private String nickname;
    private String title;
    private LocalDate diaryDate;
    private Boolean access;
    private String content;
    private DiaryFrame diaryFrame;

}
