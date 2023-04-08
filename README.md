# MySenierProject
졸작
# PlaylistMate

 ** 음악 플레이리스트 공유 웹 사이트**

> 정리.

* 실행 후 localhost:8080

1.  controller / domain / repository / service 로 나누는 역할에 대해 명확하게 이해

   * controller로 외부요청 받고 service로 비즈니스 로직 만들고 repository에서 데이터 저장.
   * 정형화되어있는 패턴.
   * 컨트롤러 - 서비스 - 레파지토리 연결 
   * 도메인 : 비즈니스 객체. 서비스와 연결되어있다?는 의미?

2.  MemoryMemberRepository -> db에 연결하는 repository로 변경할 거임. 

3. MemberController가 MemberService를 통해 회원가입 및 데이터 조회하도록 기능
   * @Controller로 스프링 컨테이너에 담고 @autowired로 연결하면 컨테이너가 알아서 꺼내줌. di(자동의존관계 주입)  ---이거 맞나
     스프링 빈 - 일하는 단위. 서블릿에서는 머였더라.
   * 스프링 빈에 직접 등록하는 방식보다 이게 편하긴 함. 
     근데 직접 등록 방식을 쓰려 하는 이유 : 빈 때문에. 멤버서비스나 컨트롤러 수정안하고 레파지토리 갈아끼울려고.
                                         지금은 회원가입하면 메모리에 저장됨. 서버 끄면 다 날라감.
4. test 코드를 작성하는 이유에 대해 명확한 이해
   * 페이지 몇 개 안 될 때는 하나하나 빌드해볼 수 있지만 많아지면 힘듬. 테스트 코드 작성하면 개네만 한 번에 돌려보면 됨.
     

5. 패키지를 포함한 하위폴더들만 컴포넌트 가능. 해당 패키지 밖에서 컴포넌트 불가.
  
6. 스프링 컨테이너는 스프링 빈을 싱글톤으로 등록함. 객체의인스턴스 한개만 생성되는거.

7. 회원가입 구현 - db 연결 없이 기능 구현은 월 ~ 화까지 가능할 듯? -> 했음.
   db연결도 오래 걸릴 건 아닌데 스프링에 대한 이해도를 높이는게 우선일듯.

8. 노트북 - 이클립스 설정 해놓기

9. 컨트롤러가 스태틱 파일보다 우선순위가 높음. 그래서 index.html이 아닌 home.html 호출됨   
```
@Controller
public class HomeController {	
	@GetMapping("/")
	public String home() {
		return "home"; 
	}
} 
```
10. name null값일시 회원가입 불가 구현하기 : 중복 회원가입 불가. 비즈니스 로직이기 때문에 서비스에 넣어야함.

11. 회원가입 페이지만 구현함. '회원목록'에 가입된 회원 이름 나옴 . jsp파일 일단 html로 바꿨음. 일단 가입할 때 이름만 받는 걸로 해놈.
    존재하는 이름으로 가입시 오류메세지 - 바꿨더니 여기에서 오류남. 

12. 
```
@GetMapping("/members/new") //get벙삭운 주로 조회할 때.
	public String createForm() {
		return "members/createMemberForm"; //createMemberForm으로 이동. 
	}
```
```
<form action="/members/new" method="post">
			<div class="int-area">
				<input type="text" name="name" id="name"
				autocomplete="off" required>
				<label for="id">아이디</label>
			</div>
			<input type="checkbox" />
			중복 체크
```

   1. 클라이언트 /members/new url 요청 
   2. @GetMapping("/members/new")로 인해 createForm() 실행 
   3. createMemberForm.html 파일 찾아 렌더링.
