package com.spring.yagaza.web.domain.embeddedId;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Embeddable
@Getter @Setter
public class TripCommentId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long tripBoardNo;
	
	@Column(name = "trip_comment_no")
	private Long tripCommentNo;
}
