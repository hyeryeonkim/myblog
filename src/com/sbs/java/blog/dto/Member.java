package com.sbs.java.blog.dto;

import java.util.Map;

public class Member extends Dto {
	private String loginId;
	private String name;
	private String nickname;
	private String loginPw;
	
	public Member() {
		
	}
	
	//row들의 값들을 형변환 해주는 이유는 Map은 Object 타입을 리턴하기 때문!!
	//필요한, 맞는 타입으로 꼭 형변환을 해주어야 한다! 
	public Member(Map<String, Object> row) {
		super(row);
		this.loginId = (String)row.get("loginId");
		this.name = (String)row.get("name");
		this.nickname = (String)row.get("nickname");
		this.loginPw = (String)row.get("loginPw");
		
	}
	
	


	@Override
	public String toString() {
		return "Member [loginId=" + loginId + ", name=" + name + ", nickname=" + nickname + ", loginPw=" + loginPw
				+ ", dto=" + super.toString() + "]";
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getLoginPw() {
		return loginPw;
	}

	public void setLoginPw(String loginPw) {
		this.loginPw = loginPw;
	}
	
	
}
