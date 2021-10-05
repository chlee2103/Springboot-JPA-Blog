package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController // html파일이 아니라 data를 리턴해주는 controller
public class DummyControllerTest {

	@Autowired // 클래스가 메모리에 뜰 때 같이 뜬다. 의존성주입(DI)
	private UserRepository userRepository;
	
	// save함수는 id를 전달하지 않으면 insert를 해주고
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해요.
	// email, password
	
	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		
		return "삭제되었습니다. id : "+id;
	}
	
	
	
	@Transactional // save함수를 사용하지 않아도 update가 된다. (더티 체킹)
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { 
		// json 데이터를 요청 => JAVA Object (MessageConverter의 Jackson라이브러리가 변환해서 받아준다. 그때 필요한 것 @RequestBody)
		System.out.println("id : "+ id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다. ");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
//		userRepository.save(user);
		// DB의 더티체킹 : 어떤 트랜젝션에 commit할 것을 모아 두었다가 한번에 commit 하는 것
		// JPA의 더티 체킹 : 변경을 감지하는 것(찌꺼기를 체크해서 날리는 것)
		return user;
	}
	
	
	// http://localhost:8000/blog/dummy/user
	// 전체 user 찾기 - .findAll()
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 한 페이지당 2건에 데이터를 리턴받아 볼 예정
	// http://localhost:8000/blog/dummy/user?page=1     , page는 0부터 시작
	@GetMapping("/dummy/user") // 2건씩 가져오고 sort는 id이고 최신순으로 정렬
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable){
		Page<User> pageUsers = userRepository.findAll(pageable);
		List<User> users = pageUsers.getContent();
		return users;
	}
	
	// {id} 주소로 파라메터를 전달 받을 수 있음.
	// http://localhost:8000/blog/dummy/user/3
	// id로 user정보 찾기 - .findById(id)
	@GetMapping("/dummy/user/{id}")
	public User datail(@PathVariable int id) {
		// user/4을 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 될 것 아냐?
		// 그럼 return null이 리턴이 되나자.. 그럼 프로그램에 문제가 있지 않겠니?
		// Optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해
		
		// 1번째 방법  : .get() - 나는 그럴일 없으니 그냥 리턴해! (위험)
		// User user = userRepository.findById(id).get()
		
		// 2번째 방법 : .orElseGet() - 없으면 너가 객체하나 만들어서 넣어줘
		// Supplier : 익명객체 / 인터페이스를 new하려면 익명객체를 만들어야 한다.
/*		
		User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
			// DB에 값이 없을 때 실행.
			@Override
			public User get() {
				return new User(); // 빈객체를 User에 넣어주기 ( 실행시 다 null로 리턴된다)
			}
		});
*/
		
		// 3번째 방법 .orElseThrow() - 에러메세지 보내주기
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다."); // 후에는 인터셉터하여 에러페이지를 보여주어야 한다.
			}
		});
		
		// 4번째 방법  ()->{} (람다식) - 익명으로 처리할 수 있어서 리턴타입은 전혀 몰라도 된다.
/*		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
		});
*/		
		// 요청 : 웹브라우저
		// user 객체 = 자바 오브젝트
		// 변환 (웹브라우저가 이해할 수 있는 데이터) -> JSON ( 원래 Gson 라이브러리를 사용하여 변환)
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
		// 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에 던져준다.
		return user;
	}
	
	// http://localhost:8000/blog/dummy/join (요청)
	// http의 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	public String join( User user) { // Key=value (약속된 규칙)
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println(user.toString());
		
		user.setRole(RoleType.USER);
		userRepository.save(user); // 회원 insert
		return "회원가입이 완료되었습니다.";
	}
}
