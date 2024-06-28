package com.sample.domain.diary.dto;

import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class DiaryDetailRes {

    private String title;

    private LocalDate diaryDate;

    private String nickname;

    private String content;

    private Integer diaryFrame;

}
