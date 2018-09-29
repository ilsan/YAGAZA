package com.spring.yagaza.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.spring.yagaza.web.domain.TripBoard;

@Mapper
@Repository
public interface TripBoardRepository {
	
	public List<TripBoard> findByBoardList();

}
