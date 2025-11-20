package com.dataproviders.api.bean;

import com.opencsv.bean.CsvBindByName;

public class UserBean {
	@CsvBindByName(column = "username")
	private String username;
	@CsvBindByName(column = "password")
	private String password;

	
	public UserBean() {
		
	}
	@Override
	public String toString() {
		return "UserBean [username=" + username + ", password=" + password + "]";
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
	
	
}
