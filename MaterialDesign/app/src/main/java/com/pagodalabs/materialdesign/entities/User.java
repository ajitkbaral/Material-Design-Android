package com.pagodalabs.materialdesign.entities;

import java.io.Serializable;

public class User implements Serializable{
	
	private Integer userId;
	private Integer professionalId;
	private String username;
	private String password;
	private String token;
	private String tokenBegin;
	private String tokenEnd;
	
	public User() {
		
	}

	public User(Integer userId, Integer professionalId, String username, String password, String token, String tokenBegin, String tokenEnd) {
		
		this.userId = userId;
		this.professionalId = professionalId;
		this.username = username;
		this.password = password;
		this.token = token;
		this.tokenBegin = tokenBegin;
		this.tokenEnd = tokenEnd;
	}
	

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProfessionalId() {
		return professionalId;
	}

	public void setProfessionalId(Integer professionalId) {
		this.professionalId = professionalId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenBegin() {
		return tokenBegin;
	}

	public void setTokenBegin(String tokenBegin) {
		this.tokenBegin = tokenBegin;
	}

	public String getTokenEnd() {
		return tokenEnd;
	}

	public void setTokenEnd(String tokenEnd) {
		this.tokenEnd = tokenEnd;
	}

	@Override
	public String toString() {
		return "User [token=" + token + ", tokenBegin=" + tokenBegin
				+ ", tokenEnd=" + tokenEnd + "]";
	}

	
	
	

}
