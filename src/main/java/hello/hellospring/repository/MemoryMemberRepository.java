package hello.hellospring.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

import hello.hellospring.domain.Member;

@Repository //요것도 컨테이너 인식위해.
public class MemoryMemberRepository implements MemberRepository{

	private static Map<Long, Member> store= new HashMap<>();
	private static long sequence = 0L; //0,1,2등의 값 생성.
	
	@Override
	public Member save(Member member) {
	
		member.setId(++sequence); //아이디 부여
		store.put(member.getId(), member); //저장.
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {

		return Optional.ofNullable(store.get(id)); //null일 경우 클라이언트에서 먼가 동작가능.
	}

	@Override
	public Optional<Member> findByName(String name) {
		return store.values().stream()
				.filter(member -> member.getName().equals(name))
				.findAny();
	
	}

	@Override
	public List<Member> findAll() {
	
		return new ArrayList<> (store.values());
	}

	public void clearStore() {
		//test가 순서상관없이 진행. 한번 돌때 만다 클리어위해 넣음.
		store.clear();
	}

}
//검증 : 테스트 케이스 작성 ㄱ