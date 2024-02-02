package Hyeok.firstproject;

import Hyeok.firstproject.repository.JdbcWordRepository;
import Hyeok.firstproject.repository.WordRepository;
import Hyeok.firstproject.service.WordService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public WordService wordService(){
        return new WordService(wordRepository());
    }

    @Bean
    public WordRepository wordRepository(){
        // return new MemoryWordRepository();
        return new JdbcWordRepository(dataSource);
    }

}
