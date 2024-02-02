package Hyeok.firstproject.controller;

import Hyeok.firstproject.domain.Word;
import Hyeok.firstproject.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.AttributedString;
import java.util.List;

@Controller
public class WordController {
    private final WordService wordService;

    @Autowired // 스프링 빈에 등록된 객체를 스프링 컨테이너에서 가져와서 자동 연결
    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/words/new")
    public String createForm(){
        return "words/createWordForm";
    }

    @PostMapping("/words/new") // 데이터를 form에 저장
    public String create(WordForm form){
        Word word =new Word();
        word.setTerm(form.getTerm());
        word.setDefinition(form.getDefinition());

        System.out.println("Term = " + word.getTerm());
        System.out.println("Definition = " + word.getDefinition());

        wordService.join(word);

        return "redirect:/words/new";
    }

    @GetMapping("/words")
    public String wordList(Model model){ // 모델에 데이터를 담아서 view로 넘김
        List<Word> words = wordService.findWords();
        model.addAttribute("words",words);
        return "words/wordList";
    }
}
