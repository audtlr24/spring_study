package controller;

public class ChangePwdCommand {
	private String currentPassword;
	private String newPassword;
	
	public String getCurrentPassword() {
		return currentPassword;
	}
	
	public void setCurrentPassword(String currentPwd) {
		this.currentPassword = currentPwd;
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
