package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // 빌더패턴
//ORM -> Java(다른언어) Object -> 테이블로 맵핑해주는 기술
@Entity // User클래스가 MySQL에 테이블이 생성이 된다. (class table 화)
// @DynamicInsert // insert시 null인 컬럼을 제외 시키고 인설트해준다.
public class User {
	
	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; //시퀀스(오라클), auto_increment(MySQL)
	
	@Column(nullable = false, length = 30) // Not Null, 길이 20
	private String username; // 아이디
	
	@Column(nullable = false, length = 100) // 123456 => 해쉬 (비밀번호 암호화)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	

	// @ColumnDefault("user")
	// DB는 RoleType이라는게 없다
	@Enumerated(EnumType.STRING) // String 데이터타입 지정
	private RoleType role;  // Enum을 쓰는게 좋다 -> 어떤 데이터의 도메인(범위)을 만들수 있다. 
										   // admin, user, manager(권한)
	
	@CreationTimestamp // 시간이 자동 입력 - SYSDATE(오라클)
	private Timestamp createDate;
}