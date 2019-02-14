package com.spring.yagaza.web.repository;

import java.util.Map;

import com.spring.yagaza.web.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Long> {
	
	//int userAdd(Map<String, Object> map);

	@Query(value = "SELECT u FROM User u WHERE u.userId = :id")
	User idCheck(String id);

}
