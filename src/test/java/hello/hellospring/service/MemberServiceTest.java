package hello.hellospring.service;

import static org.junit.Assert.*;

import org.assertj.core.api.Assertions; //static import 와의 차이
import org.junit.Test;

import hello.hellospring.domain.Member;

class MemberServiceTest {

	
	MemberService memberService = new MemberService();
	
	@Test
	void 회원가입() { //테스트는 한글로 적어도 상관없음.
		//given
		Member member = new Member();
		member.setName("hello");
				
		//when
		Long saveId = memberService.join(member);
		
		//then
		Member findMember = memberService.findOne(saveId).get();
		Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
	}

	@Test
	public void 중복회원예외() {
		//given 
		Member member1 = new Member();
		member1.setName("spring");
		
		Member member2 = new Member();
		member2.setName("spring");
		
		//when
		memberService.join(member1);
		memberService.join(member2);
		//then
		
	}
	
	@Test
	void testFindMembers() {
		fail("Not yet implemented");
	}

	@Test
	void testFindOne() {
		fail("Not yet implemented");
	}

}
