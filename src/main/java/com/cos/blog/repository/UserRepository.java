package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
 JpaRepository<User, Integer>
 - 해당 JpaRepository는 User테이블을 관리한다. 그리고 User테이블의 PK는 Integer이다.
- DAO(Data Access Object)
- Bean으로 등록되나여? == 스프링 IoC에서 객체를 가지고 있나요? 그래야 필요한 곳에서 인젝션하여 DI를 할 수 있다.
- 자동으로 Bean등록이 된다.
- @Repository 생략가능하다.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    // SELECT * FROM user WHERE username = 1?;
    Optional<User> findByUsername(String username);
}

// JPA Naming 쿼리
// SELECT * FROM user WHERE username =? AND passwoer =?;
// User findByUsernameAndPassword(String username, String password);

    /* 위와 동일
    @Query(value = "SELECT * FROM user WHERE username =?1 AND passwoer =?2", nativeQuery = true)
    User login(String username, String password);
     */
