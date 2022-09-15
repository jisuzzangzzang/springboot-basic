package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // Bean을 자동 생성해줌

    // select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}


    /*
    < 스프링 데이터 JPA 제공 기능 >
    · 인터페이스를 통한 기본적인 CRUD
    · 'findByName()', 'findByEmail()' 처럼 메소드 이름 만으로 조회 기능 제공
    · 페이징 기능 자동 제공

    · 실무에서는 JPA와 스프링 데이터 JPA 기본으로 사용, 복잡한 동적 쿼리는 Querydsl 라이브러리 사용
    · 이 조합으로 해결하기 어려운 쿼리는 JPA가 제공하는 네이티브 쿼리 사용하거나, 스프링 JdbcTemplate 사용
    */