package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 자바 코드로 직접 스프링 빈 등록하기
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired // 생성자가 하나일 경우 생략 가능하지만 작성해줬음.
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

/*
    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }
*/
}

/*
    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource);
        // return new JdbcTemplateMemberRepository(dataSource);
        // return new JpaMemberRepository(em);
    }
}*/

    /*
    · 실무에서는 주로 컴포넌트 스캔을 사용한다. @Controller, @Service, @Repository..
    · 정형화되지 않거나, 상황에 따라 구현 클래스를 변경해야하면 스프링 빈으로 등록하는 것이 좋다.
    ·  @Autowired를 통한 DI는 'helloController', 'MemberService' 등과 같이 스프링이 관리하는 객체에서만 동작,
       스프링 빈으로 등록하지 않고 내가 직접 생성한 객체에서는 동작하지 않는다.
    · OCP (Open-Closed Principle) / 개방-폐쇄의 원칙
      : 확장에는 열려있고, 수정·변경에는 닫혀있다.
    · 스프링의 DI (Dependencies Injection)을 사용하면 기존 코드를 전혀 손대지 않고,
      설정만으로 구현 클래스를 변경할 수 있다.
    */
