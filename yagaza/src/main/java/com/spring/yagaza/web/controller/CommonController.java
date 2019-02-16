package com.spring.yagaza.web.controller;

import com.spring.yagaza.web.domain.ImgFile;
import com.spring.yagaza.web.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CommonController {

    @Autowired
    private FileService fileService;
	
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@RequestMapping(value="/fileUpload/img" , method = RequestMethod.POST)
	public ResponseEntity<String> fileUpload(MultipartHttpServletRequest multi){
		
		logger.info(multi.getSession().getServletContext().toString());
		
		MultipartFile file = multi.getFile("uploadFile");
		String categoryCode = multi.getParameter("categoryCode");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date now = new Date();
        String strDate = sdf.format(now);

        String originalFileName = file.getOriginalFilename();  //�떎�젣�뙆�씪紐�
        String ext = originalFileName.substring(originalFileName.lastIndexOf('.')); // �솗�옣�옄
        String saveFileName = strDate+ext; // ���옣�뙆�씪紐�
        
		//濡쒖뺄 �씠誘몄��뾽濡쒕뱶寃쎈줈 �꽕�젙 // TODO: 異뷀썑 寃쎈줈 蹂�寃쏀빐�빞�빀�땲�떎. �삉, 寃쎈줈媛숈� �젙蹂대뱾�� properties�굹, �떎瑜� 諛⑸쾿�쑝濡� �븳怨녹뿉�꽌 愿�由ы븯�룄濡� �빀�떆�떎.
		String path = "/upload/img";
		String realPath = "C:/Users/ShipJH/Pictures/imgUpload" + path;
		logger.info("realPath >> " + realPath + path);
		
        File dir = new File(realPath);
        if(!dir.isDirectory()) { 
            dir.mkdirs();
        }
        
        logger.debug("�떎�젣�뙆�씪紐� :" + originalFileName);
        logger.debug("���옣�뙆�씪紐� :" + saveFileName);
        logger.debug("�뙆�씪 �겕湲� :" + file.getSize());
        logger.debug("�뙆�씪 ���엯 :" + file.getContentType());
        logger.debug("�뙆�씪 �솗�옣�옄 : " + ext);
        
        if(!(".jpg".equals(ext.toLowerCase()) || ".png".equals(ext.toLowerCase()) || ".jpeg".equals(ext.toLowerCase()))) {
            logger.error("[error] >> filename extension is not jpg, jpeg, png");
            return new ResponseEntity<>("", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }
		
        File saveFile = new File(realPath,saveFileName);
        HttpStatus uploadFileResult = saveFile(file,saveFile);
        if(!uploadFileResult.equals(HttpStatus.OK)) {
            return new ResponseEntity<>("", uploadFileResult);
        }else {
            logger.debug(">> DB INSERT <<");

            ImgFile imgVo = ImgFile.builder()
                    .fileOrgNm(originalFileName)
                    .fileSaveNm(saveFileName)
                    .fileDir(path)
                    .fileSize(file.getSize())
                    .contentType(file.getContentType())
                    .fileCategory(categoryCode)
                    .build();

//            service.insertFileInfo(imgVo);
// 			  TODO: �뵒鍮� insert 異붽�.
        }
		
		return new ResponseEntity<>(path+saveFileName, HttpStatus.OK);
	}
	
	
	/**
	 * �씠誘몄� �뾽濡쒕뱶�떆 �삤瑜섏뿼�젮 泥댄궧
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

    @PostMapping("/fileUpload/image")
    @ResponseBody
    public ResponseEntity<?> handleFileUpload(@RequestParam("uploadFile") MultipartFile file) {

        try {
            ImgFile uploadedFile = fileService.store(file);
            System.out.println(uploadedFile.toString());
            return ResponseEntity.ok().body("/fileUpload/image/" + uploadedFile.getFileNo());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/fileUpload/image/{fileId}")
    @ResponseBody
    public ResponseEntity<?> serveFile(@PathVariable Long fileId) {
        try {
            ImgFile uploadedFile = fileService.load(fileId);
            HttpHeaders headers = new HttpHeaders();

            Resource resource = new UrlResource(Paths.get(uploadedFile.getFileDir(), uploadedFile.getFileSaveNm()).toUri());
            String fileName = uploadedFile.getFileOrgNm();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
            headers.setContentType(MediaType.valueOf(uploadedFile.getContentType()));
           /* if (MediaUtils.containsImageMediaType(uploadedFile.getContentType())) {
                headers.setContentType(MediaType.valueOf(uploadedFile.getContentType()));
            } else {
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            }*/

            return ResponseEntity.ok().headers(headers).body(resource);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
