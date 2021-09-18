package com.cos.blog.test;
// 기본 패기지명을 맞추어 주어야 한다. com.cos.blog이하로 패키지 작성!
// ConponentScan : 필요한 것들을 메모리 로드 IoC 싱글톤
// com.cos.test 이런식으로 할 때 스프링이 스캔을 하지 않는다. (진행불가!)
// Spring -> IoC(제어의 역전)
// 너가 new하지마 Spring 내가 new할게
// 싱글톤 + 레퍼런스 변수를 스프링이 관리하겠다.

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

 // 스프링이 com.cos.blog 패키지 이하를 스캔하여 모든 파일을 메모리에 new하는 것은 아니다.
 // 특정 어노테이션이 붙어있는 클래스 파일들을 new해서 (IoC) 스프링 컨테이너에 관리해준다.
@RestController
public class BlogControllerTest {
	
	// http://localhost:8080/test/hello
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello spring boot</h1>";
	}
}
