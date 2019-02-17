package com.spring.yagaza.web.service;

import java.util.List;

import com.spring.yagaza.web.domain.TripBoard;

public interface TripBoardService {
	
	/**
	 * 여행게시판 목록 조회하기
	 * @return
	 */
	List<TripBoard> findByBoardList();

	/**
	 * 여행게시판 상세페이지
	 * @param tripBoardNo
	 * @return
	 */
	TripBoard findByBoardDetail(Long tripBoardNo);

	/**
	 * 여행등록 테스트
	 * @param tripBoard
	 */
	void save(TripBoard tripBoard);

}
