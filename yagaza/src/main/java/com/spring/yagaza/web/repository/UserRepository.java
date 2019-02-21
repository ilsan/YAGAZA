package com.spring.yagaza.web.repository;

import com.spring.yagaza.web.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
	
	//int userAdd(Map<String, Object> map);


	User findByUserId(String userId);

}
