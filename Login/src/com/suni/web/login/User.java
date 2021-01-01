package com.suni.web.login;

public class User {
	int id;
	String uName;
	String password;
	String email;
	String country;
	public User(int id, String uName, String password, String email, String country) {
		super();
		this.id = id;
		this.uName = uName;
		this.password = password;
		this.email = email;
		this.country = country;
	}
	public User(String uName, String password, String email, String country) {
		super();
		this.uName = uName;
		this.password = password;
		this.email = email;
		this.country = country;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", uName=" + uName + ", password=" + password + ", email=" + email + ", country="
				+ country + "]";
	}
	

}
