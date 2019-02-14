package com.spring.yagaza.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.yagaza.web.domain.TripBoard;

public interface TripBoardRepository extends JpaRepository<TripBoard, Long> {
	
	/**
	 * 여행게시판 목록 조회하기
	 * @return
	 */
	//Page<TripBoard> findByBoardList();

	/**
	 * 여행게시판 상세페이지
	 * @param tripBoardNo
	 * @return
	 */
	//TripBoard findByBoardDetail(String tripBoardNo);

}
