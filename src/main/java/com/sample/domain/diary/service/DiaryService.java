package com.sample.domain.diary.service;

import com.sample.domain.diary.domain.Diary;
import com.sample.domain.diary.dto.CreateDiaryReq;
import com.sample.domain.diary.dto.CreateDiaryRes;
import com.sample.domain.diary.repository.DiaryRepository;
import com.sample.global.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    public ResponseEntity<?> createDiary(CreateDiaryReq createDiaryReq) {
        Diary diary = Diary.builder()
                .nickname(createDiaryReq.getNickname())
                .title(createDiaryReq.getTitle())
                .diaryDate(createDiaryReq.getDiaryDate())
                .access(createDiaryReq.getAccess())
                .content(createDiaryReq.getContent())
                .diaryFrame(createDiaryReq.getDiaryFrame())
//                .code()
                .build();
        diaryRepository.save(diary);
        CreateDiaryRes createDiaryRes = CreateDiaryRes.builder()
                .id(diary.getId())
                .code(diary.getCode())
                .build();
        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information(createDiaryRes)
                .build();
        System.out.println(apiResponse.getInformation());

        return ResponseEntity.ok(apiResponse);
    }
}
