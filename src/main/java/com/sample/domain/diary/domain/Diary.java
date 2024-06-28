package com.sample.domain.diary.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    private Long id;

    private String nickname;

    private String title;

    private LocalDate diaryDate;

    private Boolean access;

    @Column(length = 500)
    private String content;

    private Integer diaryFrame;

    private String code;

    @Builder
    public Diary(String nickname, String title, LocalDate diaryDate, Boolean access, String content, Integer diaryFrame, String code) {
        this.nickname = nickname;
        this.title = title;
        this.diaryDate = diaryDate;
        this.access = access;
        this.content = content;
        this.diaryFrame = diaryFrame;
        this.code = code;
    }
}

