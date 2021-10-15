package com.cos.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder 
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content; // 썸머노트 라이브러리 <html>태그가 섞여서 디자인 됨.
	
	private int count;// 조회수
	
	// fetch = FetchType.EAGER --> select 할때 무조건 들고와라
	@ManyToOne (fetch = FetchType.EAGER)// 연관관계 맺기  Many = Board, User = One - 한명의 유저는 여러개의 게시글을 쓸 수 있다.
	@JoinColumn(name="userId")
	private User user; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.
	

	// 1개의 게시글엔 여러개의 리플이 달릴 수 있다.
	// select 하기 위한 코드
	@OneToMany (mappedBy = "board", fetch = FetchType.EAGER) // mappedBy 연관관계의 주인이 아니다 ( 난 FK가 아니예요) DB에 컬럼을 만들지 마세요
	@JsonIgnoreProperties({"board"}) // board를 통해 reply를 뽑을땐 board데이터를 뽑지 않겠다.
	private List<Reply> replys;
	
	@CreationTimestamp // 알아서 시간등록
	private Timestamp createDate;
}
