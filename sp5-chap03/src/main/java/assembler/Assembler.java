package assembler;

import spring.ChangePasswordService;
import spring.MemberRegisterService;

import spring.MemberDao;

public class Assembler {
	
	private MemberDao memberDao;
	private MemberRegisterService regSvc;
	private ChangePasswordService pwdSvc;
	
	public Assembler() {
		memberDao = new MemberDao();
		regSvc = new MemberRegisterService(memberDao); //생성자를 통해서 객체를 주입받는 방법을 활용
		pwdSvc = new ChangePasswordService(); 
		pwdSvc.setMemberDao(memberDao);  //setter를 정의해서 객체를 주입받는 방법을 활용
	}
	
	public MemberDao getMemberDao() {
		return memberDao;
	}
	public MemberRegisterService getMemberRegisterService() {
		return regSvc;
	}
	public ChangePasswordService getChangePasswordService() {
		return pwdSvc;
	}

}
