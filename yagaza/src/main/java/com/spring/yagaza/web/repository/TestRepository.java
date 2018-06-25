package com.spring.yagaza.web.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.spring.yagaza.web.domain.testVo;

@Mapper
@Repository
public interface TestRepository {
	
	public List<testVo> List();

}
