package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.ChangePasswordService;
import spring.MemberNotFoundException;
import spring.WrongIdPasswordException;

public class MainForCPS {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		ChangePasswordService cps = ctx.getBean("changePwdSvc",ChangePasswordService.class);
		
		try {
			cps.changePassword("audtlr24@audtlr24", "1234", "1111");
			System.out.println("암호를 변경했습니다");
		}catch(MemberNotFoundException e){
			System.out.println("일치하는 회원이 없습니다");
		}catch(WrongIdPasswordException e) {
			System.out.println("암호가 잘못되었습니다");
		}
		
		ctx.close();

	}

}
