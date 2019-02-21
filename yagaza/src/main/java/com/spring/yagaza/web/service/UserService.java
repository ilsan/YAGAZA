package com.spring.yagaza.web.service;

import com.spring.yagaza.web.domain.User;

public interface UserService {
	
	String userAdd(User user);
	
	String idCheck(String id);
}
