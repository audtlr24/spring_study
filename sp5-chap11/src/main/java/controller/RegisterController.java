package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.DuplicateMemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

@Controller
public class RegisterController {
	
	private MemberRegisterService memRegSvc;
	
	public void setMemRegSvc(MemberRegisterService memRegSvc) {
		this.memRegSvc = memRegSvc;
	}
	
	@RequestMapping("/register/step1")
	public String handleStep1() {
		return "register/step1";
	}
	
	@PostMapping("/register/step2")
	public String handleStep2(
			@RequestParam(value="agree",defaultValue="false") Boolean agreeVal, Model model) {
		if (!agreeVal) {
			return "register/step1";
		}
		model.addAttribute("registerRequest",new RegisterRequest());
		return "register/step2";
	}
	
	@GetMapping("/register/step2")
	public String handleStep2Get() {
		return "redirect:/register/step1";
	}
	
	@PostMapping("/register/step3")
	public String handleStep3(RegisterRequest regReq) { //세터 매서드를 포함하는 객체를 넣어주면, 일치하는 요청 파라미터의 값을 객체로 담아주는 기능을함
		try {
			memRegSvc.regist(regReq);
			return "register/step3";
		}catch (DuplicateMemberException e) {
			return "register/step2";
		}
	}
}
