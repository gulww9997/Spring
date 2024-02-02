package Hyeok.firstproject.service;

import Hyeok.firstproject.domain.Word;
import Hyeok.firstproject.repository.MemoryWordRepository;
import Hyeok.firstproject.repository.WordRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // 스프링 컨테이너와 함께 테스트
@Transactional // 테스트 때 DB에 넣었던 데이터 롤백
class WordServiceIntegrationTest {

    @Autowired WordService wordService;
    @Autowired WordRepository wordRepository;

    @Test
    void 단어등록() {
        //given
        Word word = new Word();
        word.setTerm("spring");
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
        word1.setTerm("spring");
        word1.setDefinition("봄");

        Word word2 = new Word();
        word2.setTerm("spring");
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
}