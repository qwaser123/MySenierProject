package hello.hellospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;

@Controller //스프링 컨테이너가 MemberController객체 생성해서 넣어둠.
public class MemberController {

	private final MemberService memberService;

	@Autowired // 연결.스프링 빈의 멤버서비스 객체를 넣어줌.di가 요거엿구만.
	public MemberController(MemberService memberService) {
		//생성자 단축키 기억.
		this.memberService = memberService;
	}
	
	@GetMapping("/members/new") //get벙삭운 주로 조회할 때.
	public String createForm() {
		return "members/createMemberForm"; //createMemberForm으로 이동. 
	}
	
	@PostMapping("/members/new") //post방식은 데이터를 폼에 넣어 전달할 때.
	public String create(MemberForm form) {
		Member member = new Member();
		member.setName(form.getName());
		
		memberService.join(member);
		
		return "redirect:/";
	}
	
	@GetMapping("/members") //가입한 회원목록
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members); //model 키: "members". 이안에는 list로 모든회원을 조회해 넣엄놈.
		return "members/memberList";
	}
}
