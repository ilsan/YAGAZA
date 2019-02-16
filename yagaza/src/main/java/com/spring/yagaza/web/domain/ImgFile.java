package com.spring.yagaza.web.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "img_file")
@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ImgFile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fileNo;

	@Column(nullable = false)
	private String fileOrgNm;

	@Column(nullable = false)
	private String fileSaveNm;

	@Column(nullable = false)
	private String fileDir;

	@Column(nullable = false)
	private String contentType;

	@Column(nullable = false)
	private Long fileSize;

	@Column(nullable = false)
	private String fileCategory;

	@ManyToOne
	@JoinColumn(name = "board_id")
	private TripBoard tripBoard;

	@Column
	private LocalDateTime regDate;

	@Column
	private String memo;
}
