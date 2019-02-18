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
	
	public Long Useradd(User user) {
		
		/*Map<String, Object> map = new HashMap<String,Object>();
		
		map.put("name", user.getName());
		map.put("id", user.getId());
		map.put("password", user.getPassword());
		map.put("email", user.getEmail());
		
		System.out.println("==========");
		System.out.println(map);
		System.out.println("==========");*/

		return userRepository.save(user).getId();
	}
	
	public Long idCheck(String id) {

		return Optional.ofNullable(userRepository.idCheck(id).getId()).orElse(0L);
	}
}
