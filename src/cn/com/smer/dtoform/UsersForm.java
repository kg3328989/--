package cn.com.smer.dtoform;

public class UsersForm {
    private Long userId;

    private String userName;

    private String userPassword;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

	@Override
	public String toString() {
		return "UsersForm [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + "]";
	}
    
}