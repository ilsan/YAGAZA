package com.spring.yagaza.web.service;

import com.spring.yagaza.web.domain.User;

public interface UserService {
	
	public int Useradd(User user);
	
	public int idCheck(String id);

}
