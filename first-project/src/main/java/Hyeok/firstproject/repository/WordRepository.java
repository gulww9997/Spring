package Hyeok.firstproject.repository;

import Hyeok.firstproject.domain.Word;

import java.util.List;
import java.util.Optional;

public interface WordRepository {
    Word save(Word word); // 단어 저장
    Optional<Word> findById(Long id); // ID로 단어 검색
    Optional<Word> findByTerm(String term); // 용어로 뜻 검색
    List<Word> findAll(); // 등록한 모든 단어 검색

}
