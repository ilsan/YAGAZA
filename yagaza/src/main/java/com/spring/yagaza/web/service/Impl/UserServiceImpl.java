package com.spring.yagaza.web.service.Impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.yagaza.web.domain.User;
import com.spring.yagaza.web.repository.UserRepository;
import com.spring.yagaza.web.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public String userAdd(User user) {
		return userRepository.save(user) != null ? "성공" : "실패";
	}

	public String idCheck(String id) {
		return userRepository.idCheck(id) != null ? "true" : "false";
	}
}
