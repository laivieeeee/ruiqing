package com.ruiqing.dto;

import java.io.Serializable;

public class LoginUserDTO implements Serializable {

	private static final long serialVersionUID = 8933365175091322986L;
	
	/** 
	* @Fields userIP 登录用户IP
	* @since Ver 1.0
	*/   
	private String userIP;
	
	/** 用户ID */
	private String userId;

	/** 用户登录的帐号Code */
	private String userCode;

	/** 用户名称 */
	private String userName;
	
	/** 登陆时间 */
	private long loginTime;
	
	/** 最后访问时间 */
	private long lastAccessTime;
	/** 会话token */
	private String token;

	private String avatar;
	
	public String getUserIP() {
		return userIP;
	}
	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}
	public long getLastAccessTime() {
		return lastAccessTime;
	}
	public void setLastAccessTime(long lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAvatar() { return avatar; }
	public void setAvatar(String avatar) { this.avatar = avatar; }
}
