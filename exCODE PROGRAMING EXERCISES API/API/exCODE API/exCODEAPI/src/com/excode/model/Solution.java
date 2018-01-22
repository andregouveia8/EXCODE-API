package com.excode.model;

public class Solution {
	
	public String code;
	public String language;
	public Solution() {};
	
	public Solution(String code, String language) {
		super();
		this.code = code;
		this.language = language;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	

}
