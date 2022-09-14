package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello");
        return "hello"; // templates/hello.html
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model ) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // Body 부분에 값을 직접 설정, 템플릿 엔진 사용 X
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello spring"
    }

    @GetMapping("hello-api") // JSON 방식 key-value
    @ResponseBody // 1. HttpMessageConverter 동작
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); // 2. 객체 -> MappingJackson2HttpMessageConverter
                                   //    문자 -> StringConverter
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}


// 컨트롤러 : 웹 MVC의 컨트롤러 역할
// 서비스 : 핵심 비즈니스 로직 구현, 예) 회원은 중복가입 X
// 레포지토리 : 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리
// 도메인 : 비즈니스 도메인 객체, 예) 회원, 주문, 쿠폰 등 등 주로 데이터베이스에 저장하고 관리됨