package com.excode.model;

public class User {

	public String userID;
	public String password;
	public String email;
	public String name;
	
	//Constructor
	public User(String userID, String password, String email, String name) {
		super();
		this.userID = userID;
		this.password = password;
		this.email = email;
		this.name = name;
	}
	
	
	//Getters e Setters
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}