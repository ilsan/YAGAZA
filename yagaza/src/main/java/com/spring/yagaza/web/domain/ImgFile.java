package com.spring.yagaza.web.domain;

import lombok.Data;

@Data
public class ImgFile {
	private int fileNo;
	private String fileOrgNm;
	private String fileSaveNm;
	private String fileDir;
	private String contentType;
	private long fileSize;
	private String fileExt;
	private String fileCategory;
	private String regDate;
	private String memo;
}
