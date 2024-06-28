package com.sample.domain.diary.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class CreateDiaryReq {

    @Size(max = 10)
    private String nickname;

    @NotNull
    @Size(max = 40)
    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate diaryDate;

    @NotNull
    private Boolean access;

    @NotNull
    @Size(max = 300)
    private String content;

    @NotNull
    @Min(value = 1) @Max(value = 4)
    private Integer diaryFrame;

}