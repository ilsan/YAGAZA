package com.spring.yagaza.web.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.spring.yagaza.web.domain.testVo;
import com.spring.yagaza.web.repository.TestRepository;
import com.spring.yagaza.web.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestRepository test;
	
	@Override
	public List<testVo> List() {
		return test.List();
	}

	
}
