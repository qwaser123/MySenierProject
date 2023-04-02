package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //?
public class HelloController {
	
	@GetMapping("hello") //요 주소로 가면됨
	public String hello(Model model) {
		model.addAttribute("data", "hello!"); // data=hello!
		return "hello"; //hello 파일명을 찾아 랜더링해라.랜더링:서버에서 파일받아서 클라이언트한테 쏨.
		
	}
	
	@GetMapping("hello-mvc")
	public String helloMvc(@RequestParam("name") String name, Model model) {
		model.addAttribute("name", name);
		return "hello-template";
	}
	
	@GetMapping("hello-string")
	@ResponseBody //body에 내가 넣어버리겠다.html태그 같은거 없이.
	public String helloString(@RequestParam("name") String name) {
		return "hello " + name;
	}
	
	//아래는 json방식. 예전에는 xml방식 사용했지만 요즘은 거의 json
	@GetMapping("hello-api")
	@ResponseBody 
	public Hello helloApi(@RequestParam("name") String name) {
		Hello hello = new Hello();
		hello.setName(name);
		return hello;			
	}
	
	static class Hello {
		private String name; //private라 외부에서 못꺼냄, 그래서 메소드 통해 접근
		//아래->프로퍼티 접근 방식.
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}	
		}
	}



/*
 * 서버에 jar파일 넣어놓고 실행시키면 끝 정적 컨텐츠 기능: static에 파일있으면 걍 그대로 반환.
 view는 화면 관련된 일만. 비즈니스 로직, 서버 관련 등은 controller로. model에 담겨서 넘어감.
 */