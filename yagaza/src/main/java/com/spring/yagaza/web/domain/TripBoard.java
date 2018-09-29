package com.spring.yagaza.web.domain;

import lombok.Data;

@Data
public class TripBoard {

	/** 고유 일련번호 */
	private int tripBoardNo;

	/** 제목 */
	private String title;

	/** 내용 */
	private String content;

	/** 좋아요 수 */
	private int likeCnt;

	/** 조회 수 */
	private int hitCnt;

	/** 사용여부 ( default = 'Y' */
	private String useYn;
	
	/** 등록자 */
	private Integer regUser;

	/** 등록일자 ( default : cuurrent date ) */
	private String regDate;

	/** 수정자 */
	private Integer updUser;

	/** 수정일 ( update default : cuurrent date ) */
	private String updDate;
	
}
