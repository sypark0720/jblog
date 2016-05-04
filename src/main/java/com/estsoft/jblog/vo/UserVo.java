package com.estsoft.jblog.vo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class UserVo {
	private long user_id;
	@NotEmpty private String user_name;
	@NotEmpty @Email private String email;
	@NotEmpty private String password;
	private String regdate;
	
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "UserVo [user_id=" + user_id + ", user_name=" + user_name
				+ ", email=" + email + ", password=" + password + ", regdate="
				+ regdate + "]";
	}
}
