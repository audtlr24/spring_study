package spring;

import org.springframework.transaction.annotation.Transactional;

public class ChangePasswordService {
	
	private MemberDao memberDao;
	
	@Transactional
	public void changePassword(String email, String OldPwd, String NewPwd) {
		Member member = memberDao.selectByEmail(email);
		if(member == null)
			throw new MemberNotFoundException();
		member.changePassword(OldPwd, NewPwd);
		
		memberDao.update(member);
	}
	
	public void setMemberDao(MemberDao memberDao) { // setter 를 통해 외부에서 의존 객체를 주입 받는 방식
		this.memberDao = memberDao;
	}
}
