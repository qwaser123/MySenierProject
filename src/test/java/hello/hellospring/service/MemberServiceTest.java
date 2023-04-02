package hello.hellospring.service;

import static org.junit.Assert.*;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat; //static import 와의 차이

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

class MemberServiceTest {

	
	MemberService memberService;
	MemoryMemberRepository memberRepository;
	
	@BeforeEach 
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	
	@AfterEach
	public void  afterEach() {
		memberRepository.clearStore(); //clear. 
	}
	
	
	@Test
	void 회원가입() { //테스트는 한글로 적어도 상관없음.
		//given
		Member member = new Member();
		member.setName("hello");
				
		//when
		Long saveId = memberService.join(member);
		
		//then
		Member findMember = memberService.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
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
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); //이방법은 진짜 모르겠네. 오류 왤까. try catch가 편함.
		/*
		 * try { memberService.join(member2); fail(); }catch (IllegalStateException e) {
		 * assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); }
		 */
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
