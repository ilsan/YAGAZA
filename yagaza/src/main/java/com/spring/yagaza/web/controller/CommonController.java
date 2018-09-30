package com.spring.yagaza.web.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.yagaza.web.domain.ImgFile;

@Controller
public class CommonController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@RequestMapping(value="/fileUpload/img" , method = RequestMethod.POST)
	public ResponseEntity<String> fileUpload(MultipartHttpServletRequest multi){
		
		logger.info(multi.getSession().getServletContext().toString());
		
		MultipartFile file = multi.getFile("uploadFile");
		String categoryCode = multi.getParameter("categoryCode");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date now = new Date();
        String strDate = sdf.format(now);

        String originalFileName = file.getOriginalFilename();  //실제파일명
        String ext = originalFileName.substring(originalFileName.lastIndexOf('.')); // 확장자
        String saveFileName = strDate+ext; // 저장파일명
        
		//로컬 이미지업로드경로 설정 // TODO: 추후 경로 변경해야합니다. 또, 경로같은 정보들은 properties나, 다른 방법으로 한곳에서 관리하도록 합시다.
		String path = "/upload/img";
		String realPath = "C:/Users/ShipJH/Pictures/imgUpload" + path;
		logger.info("realPath >> " + realPath + path);
		
        File dir = new File(realPath);
        if(!dir.isDirectory()) { 
            dir.mkdirs();
        }
        
        logger.debug("실제파일명 :" + originalFileName);
        logger.debug("저장파일명 :" + saveFileName);
        logger.debug("파일 크기 :" + file.getSize());
        logger.debug("파일 타입 :" + file.getContentType());
        logger.debug("파일 확장자 : " + ext);
        
        if(!(".jpg".equals(ext.toLowerCase()) || ".png".equals(ext.toLowerCase()) || ".jpeg".equals(ext.toLowerCase()))) {
            logger.error("[error] >> filename extension is not jpg, jpeg, png");
            return new ResponseEntity<String>("", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }
		
        File saveFile = new File(realPath,saveFileName);
        HttpStatus uploadFileResult = saveFile(file,saveFile);
        if(!uploadFileResult.equals(HttpStatus.OK)) {
            return new ResponseEntity<String>("", uploadFileResult);
        }else {
            logger.debug(">> DB INSERT <<");
            
            ImgFile imgVo = new ImgFile();
            imgVo.setFileOrgNm(originalFileName);
            imgVo.setFileSaveNm(saveFileName);
            imgVo.setFileDir(path);
            imgVo.setFileSize(file.getSize());
            imgVo.setFileExt(ext);
            imgVo.setFileCategory(categoryCode);
            
//            service.insertFileInfo(imgVo);
// 			  TODO: 디비 insert 추가.
        }
		
		return new ResponseEntity<String>(path+saveFileName, HttpStatus.OK);
	}
	
	
	/**
	 * 이미지 업로드시 오류염려 체킹
	 * @param file
	 * @param saveFile
	 * @return
	 */
	private HttpStatus saveFile(MultipartFile file, File saveFile) {
		try {		
			file.transferTo(saveFile);
		}catch (IOException e) {
			logger.error("[error] IOException >> {} ", e);
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}catch (Exception e) {
			logger.error("[error] Exception >> {} ", e);
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}		
		return HttpStatus.OK;
	}
}
