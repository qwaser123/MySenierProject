package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

public class MemberService {

	private final MemberRepository memberRepository = new MemoryMemberRepository();
	
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
