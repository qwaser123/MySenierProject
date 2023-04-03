package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/") //8080, 첫 들가면 요거 호출. 컨트롤러를 먼저 찾기 떄문에 스태틱의 index.html은 호출 안됨.
	public String home() {
		return "home"; //home.html 호출
	}

}
