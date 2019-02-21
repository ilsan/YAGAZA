package com.spring.yagaza.web.service.Impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.yagaza.web.domain.User;
import com.spring.yagaza.web.repository.UserRepository;
import com.spring.yagaza.web.service.UserService;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public String userAdd(User user) {
		user.setRegDate(LocalDateTime.now());
		return userRepository.save(user) != null ? "success" : "fail";
	}

	public String idCheck(String id) {
		if (userRepository.findByUserId(id) != null) {
			throw new RuntimeException("사용할 수 없는 아이디 입니다.");
		}
		return "사용할 수 있는 아이디 입니다.";
	}
}
