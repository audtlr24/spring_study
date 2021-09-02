package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.ChangePasswordService;
import spring.WrongIdPasswordException;
import spring.Authinfo;

@Controller
@RequestMapping("/edit/changePassword")
public class ChangePwdContoller {
	private ChangePasswordService changePwdService;
	
	public void setChangePasswordService(ChangePasswordService changePwdService) {
		this.changePwdService = changePwdService;
	}
	
	@GetMapping
	public String form(@ModelAttribute("command") ChangePwdCommand pwdCmd) {
		return "edit/changePwdForm";
	}
	
	@PostMapping
	public String submit(@ModelAttribute("command") ChangePwdCommand pwdCmd, Errors errors, 
			HttpSession session) {
		new ChangePwdCommandValidator().validate(pwdCmd, errors);
		if(errors.hasErrors()) {
			return "edit/changePwdForm";
		}
		Authinfo authInfo = (Authinfo) session.getAttribute("authInfo");
		try {
			changePwdService.changePassword(authInfo.getEmail(), pwdCmd.getCurrentPassword(),
					pwdCmd.getNewPassword());
			return "edit/changedPwd";
		}catch(WrongIdPasswordException e) {
			errors.rejectValue("currentPassword", "notMatching");
			return "edit/changePwdForm";
		}
	}
}
