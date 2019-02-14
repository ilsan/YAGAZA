package com.spring.yagaza.web.service;

import com.spring.yagaza.web.domain.User;

public interface UserService {
	
	Long Useradd(User user);
	
	Long idCheck(String id);

}
