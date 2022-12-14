package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 컴포넌트 스캔 방식
public class MemberController {

    private final MemberService memberService;

    @Autowired // 의존관계 주입 (생성자)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        // System.out.println("memberService = " + memberService.getClass()); -> 프록시 주입 콘솔에 확인
    }

    @GetMapping("/members/new") // URL 직접 입력, 조회할 때 주로 사용
    public String CreateForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") // 데이터를 작성하여 전달할 때 사용
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
