package hello.hellospring.repository;

import static org.assertj.core.api.Assertions.assertThat; //static으로 임포트해서 앞에 assertions없애버림.

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;

class MemoryMemberRepositoryTest {

	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	@AfterEach
	public void  afterEach() {
		repository.clearStore(); //test가 순서상관없이 진행.의존관계 없어야 함. 한번 돌때 만다 데이터 클리어를위해 넣음.
	}
	
	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");
		
		repository.save(member);
		
	Member result =	repository.findById(member.getId()).get(); //optional에서 꺼내기 위해 get
	/*
	 * Assertions.assertEquals(member, ,result); //둘이 같은지 확인. 다르면 실패. 아래거가 더 직관적이고 편한듯.
	 */	
	assertThat(member).isEqualTo(result);
	}
	
	@Test
	public void findByname() {
		 Member member1 = new Member();
		 member1.setName("spring1");
		 repository.save(member1);
		 
		 Member member2 = new Member();
		 member2.setName("spring2");
		 repository.save(member2);
		 
		 Member result = repository.findByName("spring1").get();
		 
		 assertThat(result).isEqualTo(member1);
	}
	
	@Test
	public void findAll() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		List<Member> result = repository.findAll();
		assertThat(result.size()).isEqualTo(2);
	}
	
	
}

//test먼저 돌려보고 구현클래스 만드는 거 -> tdd(테스트 주도개발). 요게 tdd였구나.