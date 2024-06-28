package com.sample.domain.diary.repository;

import com.sample.domain.diary.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary,Long> {

    Optional<Diary> findByIdAndCode(Long diaryId, String code);

}
