package Hyeok.firstproject.repository;

import Hyeok.firstproject.domain.Word;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryWordRepositoryTest {
    MemoryWordRepository repository = new MemoryWordRepository();

    @AfterEach // 테스트 메소드가 끝날 때 마다 실행되는 코드
    public void afterEach(){
        repository.clearStore();
    }


    @Test
    public void save(){
        Word word = new Word();
        word.setTerm("Spring");
        word.setDefinition("봄");

        repository.save(word);

        Word result = repository.findById(word.getId()).get();
        System.out.println("result = " + (result == word));

        Assertions.assertEquals(result, word);
        assertThat(word).isEqualTo(result);
    }

    @Test
    public void findByTerm(){
        Word word1 = new Word();
        word1.setTerm("Summer");
        word1.setDefinition("여름");
        repository.save(word1);

        Word word2 = new Word();
        word2.setTerm("Fall");
        word2.setDefinition("가을");
        repository.save(word2);

        Word result = repository.findByTerm("summer").get();

        assertThat(result).isEqualTo(word1);
    }

    @Test
    public void findAll(){
        Word word1 = new Word();
        word1.setTerm("summer");
        word1.setDefinition("여름");
        repository.save(word1);

        Word word2 = new Word();
        word2.setTerm("fall");
        word2.setDefinition("가을");
        repository.save(word2);

        List<Word> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
