package spring;

public class AuthService {
	private MemberDao memberDao;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public Authinfo authtificate(String email,String password) {
		Member member = memberDao.selectByEmail(email);
		if(member==null) {
			throw new WrongIdPasswordException();
		}
		if(!member.matchPassword(password)) {
			throw new WrongIdPasswordException();
		}
		return new Authinfo(member.getId(), member.getEmail(), member.getName());
	}
}
