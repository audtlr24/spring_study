package chap09;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // MVC controller 로 작동
public class HelloController {
	
	@GetMapping("/hello") // method 가 처리할 요청 경로를 지정, GET 요청에 /hello 경로로 들어왔을때 처리
	public String hello(Model model, // model 은 view 에 결과를 전달할때 사용
		@RequestParam(value="name", required=false) String name) { //HTTP 요청에서의 parameter 값을 변수로 매핑
		model.addAttribute("greeting", "안녕하세요, "+name); // greeting 이라는 model 속성에 값을 설정
		return "hello"; // controller 처리 결과를 보여줄 뷰 이름으로 사용
	}

}
