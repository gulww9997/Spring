package Hyeok.firstproject.repository;

import Hyeok.firstproject.domain.Word;

import java.util.List;
import java.util.Optional;

public class JdbcTemplateWordRepository implements WordRepository{
    @Override
    public Word save(Word word) {
        return null;
    }

    @Override
    public Optional<Word> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Word> findByTerm(String term) {
        return Optional.empty();
    }

    @Override
    public List<Word> findAll() {
        return null;
    }
}
