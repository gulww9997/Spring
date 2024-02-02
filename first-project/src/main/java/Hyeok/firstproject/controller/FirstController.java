package Hyeok.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FirstController {
    @GetMapping("hello") // HTTP GET 요청의 /hello 경로를 처리하는 메소드 정의
    public String hello(Model model){ // 뷰에 데이터를 전달하기 위한 Spring의 Model 객체
        model.addAttribute("data","hello!!");
        return "firstHTML"; // templates에서 같은 이름의 뷰 매핑 (firstHTML.html)
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-api")
    @ResponseBody // 뷰를 거치지 않고 직접 HTTP 응답에 포함, ViewResolver한테 전달 X -> 페이지 소스가 HTML 코드가 아님
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // JSON 방식으로 리턴 (Key,Value)
    }

    public static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
