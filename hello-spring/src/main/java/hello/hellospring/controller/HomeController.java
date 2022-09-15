package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // 기존 index.html 파일은 무시된 채 우선순위인 Controller가 먼저 시행됨
    public String home() {
        return "home";
    }
}
