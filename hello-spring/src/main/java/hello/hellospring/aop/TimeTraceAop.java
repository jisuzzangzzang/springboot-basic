package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") // 타겟팅 할 패키지명
         // "execution(* hello.hellospring.service..*(..))") -> 서비스 패키지 하위에만 AOP 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}

    /*
     AOP (Aspect Oriented Programming)
     · 공통 관심 사항(cross-cutting concern) vs 핵심 관심 사항(core concern) 분리
     · AOP 적용 전
       helloController -> memberService(실제) -> memberRepository(실제)
     · AOP 적용 후
       helloController(프록시) -> helloController
       -> memberService(프록시) -> memberService(실제)
       -> memberRepository(프록시) -> memberRepository(실제)
     */
