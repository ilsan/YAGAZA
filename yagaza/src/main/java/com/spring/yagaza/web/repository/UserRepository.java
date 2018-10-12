package com.spring.yagaza.web.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRepository {
	
	public int userAdd(Map<String, Object> map);
	
	public int idCheck(String id);

}
