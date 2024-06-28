package com.sample.domain.diary.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sample.domain.diary.dto.CreateDiaryReq;
import com.sample.domain.diary.service.DiaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary")
public class DiaryController {

    private final DiaryService diaryService;


    @PostMapping("/changeGpt")
    public ResponseEntity<?> changeGpt(@RequestParam String content) throws JsonProcessingException {
//        System.out.println("createDiaryRes.getContent() = " + createDiaryRes.getContent());
        return diaryService.changeGpt(content);
    }

    @PostMapping
    public ResponseEntity<?> createDiary(@RequestBody @Valid CreateDiaryReq createDiaryRes) {
        return diaryService.createDiary(createDiaryRes);
    }
}