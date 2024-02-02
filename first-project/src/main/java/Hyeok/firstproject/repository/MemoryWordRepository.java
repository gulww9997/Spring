package Hyeok.firstproject.repository;

import Hyeok.firstproject.domain.Word;
import org.springframework.stereotype.Repository;

import java.security.Key;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// @Repository
public class MemoryWordRepository implements WordRepository {

    private static final Map<Long,Word> store = new HashMap<>(); // 동시성 문제 때문에 실무에선 ConcurrentHashMap 사용
    private static Long sequence = 0L; // 동시성 문제 때문에 실무에선 AtomicLong 사용

    @Override
    public Word save(Word word) {
        word.setId(++sequence);
        store.put(word.getId(),word);
        return word;
    }

    @Override
    public Optional<Word> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Word> findByTerm(String term) {
        return store.values().stream()
                .filter(word -> word.getTerm().equals(term))
                .findAny();
    }

    @Override
    public List<Word> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
