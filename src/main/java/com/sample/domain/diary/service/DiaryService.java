package com.sample.domain.diary.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sample.domain.diary.domain.Diary;
import com.sample.domain.diary.dto.CreateDiaryReq;
import com.sample.domain.diary.dto.CreateDiaryRes;
import com.sample.domain.diary.dto.DiaryDetailRes;
import com.sample.domain.diary.dto.RetrieveAllRes;
import com.sample.domain.diary.repository.DiaryRepository;
import com.sample.domain.gpt.service.impl.GptServiceImpl;
import com.sample.global.DefaultAssert;
import com.sample.global.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DiaryService {

    private final GptServiceImpl gptService;

    private final DiaryRepository diaryRepository;

    @Transactional
    public ResponseEntity<?> createDiary(CreateDiaryReq createDiaryReq) {

        String title = createDiaryReq.getTitle();
        StringBuffer sb = new StringBuffer();
        if (title.length() > 20) {
            sb.append(title);
            sb.insert(20, "\n");
            title = sb.toString();
        }

        Diary diary = Diary.builder()
                .nickname(createDiaryReq.getNickname())
                .title(title)
                .diaryDate(createDiaryReq.getDiaryDate())
                .access(createDiaryReq.getAccess())
                .content(createDiaryReq.getContent())
                .diaryFrame(createDiaryReq.getDiaryFrame())
                .code(generateCode())
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

        return ResponseEntity.ok(apiResponse);
    }

    public ResponseEntity<?> finalDiary(Long diaryId, String code) {
        Optional<Diary> findDiary = diaryRepository.findByIdAndCode(diaryId, code);
        DefaultAssert.isTrue(findDiary.isPresent(), "존재하지 않는 일기입니다.");
        Diary diary = findDiary.get();

        DiaryDetailRes diaryDetailRes = DiaryDetailRes.builder()
                .title(diary.getTitle())
                .diaryDate(diary.getDiaryDate())
                .nickname(diary.getNickname())
                .content(diary.getContent())
                .diaryFrame(diary.getDiaryFrame())
                .build();

        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information(diaryDetailRes)
                .build();

        return ResponseEntity.ok(apiResponse);
    }


    private String generateCode() {
        SecureRandom random = new SecureRandom();

        long randomNumber = random.nextLong(2821109907455L + 1);
        String code = Long.toString(randomNumber, 36);

        code = String.format("%8s", code).replace(' ', '0');

        return code;
    }


    public ResponseEntity<?> changeGpt(String content) throws JsonProcessingException {
        String assistantMsg = gptService.getAssistantMsg(content);
        return ResponseEntity.ok(assistantMsg);
    }

    public ResponseEntity<?> retrieveAll() {
        List<Diary> allDiary = diaryRepository.findAll();
        List<Diary> accessibleDiaries = allDiary.stream()
                .filter(Diary::getAccess)
                .collect(Collectors.toList());

        Collections.reverse(accessibleDiaries);

        List<RetrieveAllRes> retrieveAllResList = accessibleDiaries.stream()
                .map(diary -> new RetrieveAllRes(diary.getId(), diary.getTitle(), diary.getContent(), diary.getCode()))
                .collect(Collectors.toList());

        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information(retrieveAllResList)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}