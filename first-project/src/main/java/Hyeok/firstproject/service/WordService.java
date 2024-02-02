package Hyeok.firstproject.service;

import Hyeok.firstproject.domain.Word;
import Hyeok.firstproject.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// @Service
public class WordService {
    private final WordRepository wordRepository;

    // @Autowired
    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    /*
    단어 등록
     */
    public Long join(Word word){
        // 같은 이름이 있는 중복 회원 X
        validateDuplicateWord(word); // 중복 회원 검증
        wordRepository.save(word);
        return word.getId();
    }

    private void validateDuplicateWord(Word word) {
        wordRepository.findByTerm(word.getTerm())
            .ifPresent(w -> {
                throw new IllegalStateException("이미 존재하는 단어입니다.");
             });
    }

    /*
    전체 단어 조회
     */
    public List<Word> findWords(){
        return wordRepository.findAll();
    }

    public Optional<Word> findOne(Long wordId){
        return wordRepository.findById(wordId);
    }

}
