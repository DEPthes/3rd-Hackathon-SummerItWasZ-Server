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
        return diaryService.changeGpt(content);
    }

    @PostMapping
    public ResponseEntity<?> createDiary(@RequestBody @Valid CreateDiaryReq createDiaryRes) {
        return diaryService.createDiary(createDiaryRes);
    }

    @GetMapping("/{diaryId}/{code}")
    public ResponseEntity<?> finalDiary(
            @PathVariable(value = "diaryId") Long diaryId,
            @PathVariable(value = "code") String code
    ) {
        return diaryService.finalDiary(diaryId, code);
    }
    @GetMapping("/retrieveAll")
    public ResponseEntity<?> retrieveAll() {
        return diaryService.retrieveAll();
    }
}