package com.spring.yagaza.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.spring.yagaza.web.domain.TripBoard;

@Mapper
@Repository
public interface TripBoardRepository {
	
	/**
	 * 여행게시판 목록 조회하기
	 * @return
	 */
	public List<TripBoard> findByBoardList();

	/**
	 * 여행게시판 상세페이지
	 * @param tripBoardNo
	 * @return
	 */
	public TripBoard findByBoardDetail(String tripBoardNo);

}
