package Hyeok.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // Welcome Page보다 우선순위가 높음
    public String home(){
        return "home";
    }
}
