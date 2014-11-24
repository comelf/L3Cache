package org.l3cache.model;

import org.apache.ibatis.type.Alias;

@Alias("user")
public class User {
	private int userId;
	private String email;
	private String password;

	public User() {
		
	}
	
	public User(int userId, String email, String password) {
		this.userId = userId;
		this.email = email;
		this.password = password;
	}
	
	public User(String email, String password){
		this.email = email;
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public boolean matchPassword(String matcher) {
		if ( this.password==null) {
			return false;
		}
		if (matcher == null) {
			return false;
		}
		return this.password.equals(matcher);
	}
}
