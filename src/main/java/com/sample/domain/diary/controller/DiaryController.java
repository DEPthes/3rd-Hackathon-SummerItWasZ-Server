package com.sample.domain.diary.controller;

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

    @PostMapping
    public ResponseEntity<?> createDiary(@RequestBody @Valid CreateDiaryReq createDiaryRes){
        return diaryService.createDiary(createDiaryRes);
    }

}
