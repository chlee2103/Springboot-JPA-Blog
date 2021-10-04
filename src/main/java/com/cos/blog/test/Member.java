package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor // 빈생성자
//@RequiredArgsConstructor // final이 뭍은 변수만 생성자를 만들어준다
public class Member {
	private int id;
	private String username;
	private String password;
	private String email;
	
	@Builder // 생성자 사용시 파라미터의 순서와 갯수 상관없이 맘대로 세팅할 수 있다.
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	
}
