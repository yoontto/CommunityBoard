package com.hanul.member;

import java.io.Serializable;

public class MemberDTO implements Serializable{
	private String name, id, pw, addr, tel;
	private int age;
	
	public MemberDTO() {}

	public MemberDTO(String name, String id, String pw, int age, String addr, String tel) {
		super();
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.age = age;
		this.addr = addr;
		this.tel = tel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
}
