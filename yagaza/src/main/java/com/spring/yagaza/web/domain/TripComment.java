package com.spring.yagaza.web.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.spring.yagaza.web.domain.embeddedId.TripCommentId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Trip_comment")
//@Table(name = "Trip_comment")
@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class TripComment {

	@EmbeddedId
	private TripCommentId tripCommentId;
	
	@MapsId("tripBoardNo") // TripCommentId 클래스의 매핑할 변수(부모의 pk)
	@ManyToOne
	@JoinColumn(name = "trip_board_no")
	private TripBoard tripBoard; //trip_board_no pk할당 fk 복합키할당. 그냥~
	
	@Column(length = 1000)
	private String comment; // 댓글내용
	
	@Column(nullable = false)
	private Long regUser;    // 등록자
	
//    @Column(columnDefinition = "date default sysdate")
	private LocalDateTime regDate; // 등록일자 ( default : cuurrent date )

//	@Column(columnDefinition = "date default sysdate")
	private LocalDateTime updDate; // 수정일 ( update default : cuurrent date )
	
}
