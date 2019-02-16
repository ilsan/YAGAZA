package com.spring.yagaza.web.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "trip_board")
@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class TripBoard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tripBoardNo;	// 고유 일련번호

    @Column(nullable = false)
	private String title;	// 제목

    @Lob
	private String content; // 내용

    @Column(nullable = false)
	private int likeCnt;    // 좋아요 수

	@Column(nullable = false)
	private int hitCnt;     // 조회 수

	@Column(nullable = false)
	private String useYn;   // 사용여부 ( default = 'Y'

    @ManyToOne
    @JoinColumn(name = "user_id")
	private User regUser;    // 등록자

    @OneToMany(mappedBy = "tripBoard", cascade = CascadeType.ALL)
    private List<ImgFile> imgFiles;

    @Column
	private LocalDateTime regDate; // 등록일자 ( default : cuurrent date )

	// 수정자 (이건 뭔지 모르겠음)
	//private Integer updUser;

	@Column
	private LocalDateTime updDate; // 수정일 ( update default : cuurrent date )

}
