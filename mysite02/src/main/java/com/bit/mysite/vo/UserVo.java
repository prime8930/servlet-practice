package com.bit.mysite.vo;

public class UserVo {
	
	private Long no;
	private String name;
	private String email;
	private String password;
	private String gender;
	private String joinDate;
	
	public UserVo(String name, String email, String password, String gender) {
		this(name, email, gender);
		this.password = password;
	}

	public UserVo(Long no, String name) {
		this.no = no;
		this.name = name;
	}
	
	public UserVo(String name, String email, String gender) {
		this.name = name;
		this.email = email;
		this.gender = gender;
	}
	
	public UserVo(String email, String password) {
		this.email = email;
		this.password = password;
	}


	public Long getNo() {
		return no;
	}
	
	public void setNo(Long no) {
		this.no = no;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getJoinDate() {
		return joinDate;
	}
	
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	@Override
	public String toString() {
		return "UserVo [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password + ", gender="+ gender + ", joinDate=" + joinDate + "]";
	}
	
	
}
