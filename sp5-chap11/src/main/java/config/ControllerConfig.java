package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.ChangePwdContoller;
import controller.LoginContoller;
import controller.LogoutContoller;
import controller.RegisterController;
import spring.AuthService;
import spring.ChangePasswordService;
import spring.MemberRegisterService;
import survey.SurveyController;

@Configuration
public class ControllerConfig {
	
	@Autowired
	private MemberRegisterService memberRegSvc;
	@Autowired
	private AuthService authService;
	@Autowired
	private ChangePasswordService changePasswordService;
	
	@Bean
	public RegisterController registerController() {
		RegisterController contoller = new RegisterController();
		contoller.setMemRegSvc(memberRegSvc);
		return contoller;
	}
	
	@Bean
	public SurveyController surveyController() {
		return new SurveyController();
	}
	
	@Bean
	public LoginContoller loginContoller() {
		LoginContoller controller = new LoginContoller();
		controller.setAuthService(authService);
		return controller;
	}
	
	@Bean
	public LogoutContoller logoutController() {
		return new LogoutContoller();
	}
	
	@Bean
	public ChangePwdContoller changePwdContoller() {
		ChangePwdContoller controller = new ChangePwdContoller();
		controller.setChangePasswordService(changePasswordService);
		return controller;
	}

}
