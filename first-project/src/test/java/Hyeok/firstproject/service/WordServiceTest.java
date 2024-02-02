package Hyeok.firstproject.service;

import Hyeok.firstproject.domain.Word;
import Hyeok.firstproject.repository.MemoryWordRepository;
import Hyeok.firstproject.repository.WordRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class WordServiceTest {

    WordService wordService;
    MemoryWordRepository wordRepository;

    @BeforeEach // JUnit 테스트 메소드가 실행 되기 전에 실행 되는 코드
    public void beforeEach(){ // Dependency injection(DI)
        wordRepository = new MemoryWordRepository();
        wordService = new WordService(wordRepository);
    }

    @AfterEach // JUnit 테스트 메소드가 끝날 때 마다 실행되는 코드
    public void afterEach(){
        wordRepository.clearStore();
    }

    @Test
    void 단어등록() {
        //given
        Word word = new Word();
        word.setTerm("Spring");
        word.setDefinition("봄");

        //when
        Long saveId = wordService.join(word);

        //then
        Word findWord = wordService.findOne(saveId).get();
        assertThat(word.getTerm()).isEqualTo(findWord.getTerm());
    }

    @Test
    public void 중복_단어_예외(){
        //given
        Word word1 = new Word();
        word1.setTerm("Spring");
        word1.setDefinition("봄");

        Word word2 = new Word();
        word2.setTerm("Spring");
        word2.setDefinition("봄");

        //when
        wordService.join(word1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> wordService.join(word2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 단어입니다.");

        /*
        try {
            wordService.join(word2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 단어입니다.");
        }
*/
        //then
    }

    @Test
    void findWords() {
    }

    @Test
    void findOne() {
    }
}