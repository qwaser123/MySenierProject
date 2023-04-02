package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

//@Service //스프링이 올라올때 서비스란걸 인식하고 스프링 컨테이너에 집어넣음.
public class MemberService {

	private final MemberRepository memberRepository;
	
	
	public MemberService(MemberRepository memberRepository) {
		//constructor 단축키 :  alt shift s -> 
		this.memberRepository = memberRepository;
	}

//	회원가입 만들어보자
	public Long join(Member member) {
		//같은 이름의 중복회원 x. 리팩토링 개편하네
		validateDuplicateMember(member); 
		memberRepository.save(member);
		return member.getId();
		
	}

private void validateDuplicateMember(Member member) {
	Optional<Member> result = memberRepository.findByName(member.getName());
	result.ifPresent(m -> {
		throw new IllegalStateException("이미 존재하는 회원입니다."); 
		/*
		 * 예외처리. null이 아닌 먼가 존재하면 -> 실행.예전이었으면 if==null이지만 이제는 optional로 감싸버림.
		 */
		
	});
}
//전체 회원 조회
public List<Member> findMembers() {
	return memberRepository.findAll();
}

public Optional<Member> findOne(Long memberId) {
	return memberRepository.findById(memberId);
}
}
