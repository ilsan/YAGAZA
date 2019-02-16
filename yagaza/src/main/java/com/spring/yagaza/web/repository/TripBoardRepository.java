package com.spring.yagaza.web.repository;

import com.spring.yagaza.web.domain.TripBoard;
import org.springframework.data.jpa.repository.JpaRepository;

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
