package com.pjt.DTO;

public class Member {
	private String id, pw, name, tel;

	public Member(String i, String p, String n, String t) {
		id = i;
		pw = p;
		name = n;
		tel = t;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}