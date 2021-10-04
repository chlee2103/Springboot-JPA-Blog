package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		// prefix: /WEB-INF/views/  	->시작
	    // suffix: .jsp								-> 끝
		// 풀네임: /WEB-INF/views/home.jsp 
		return "home";
	}
}
