package com.spring.yagaza.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import static org.apache.commons.io.FilenameUtils.getExtension;

public class FileUploadUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);

    public static String fileSave(String uploadPath, MultipartFile file) throws IllegalStateException, IOException {

        File uploadPathDir = new File(uploadPath);

        if (!uploadPathDir.exists()) {
            uploadPathDir.mkdirs();
        }

        // 파일 중복명 처리
        String genId = UUID.randomUUID().toString();
        genId = genId.replace("-", "");

        String originalfileName = file.getOriginalFilename();
        String fileExtension = getExtension(originalfileName);
        String saveFileName = genId + "." + fileExtension;

        String savePath = calcPath(uploadPath);

        File target = new File(uploadPath + savePath, saveFileName);

        FileCopyUtils.copy(file.getBytes(), target);

        return makeFilePath(uploadPath, savePath, saveFileName);
    }

    private static String calcPath(String uploadPath) {

        Calendar cal = Calendar.getInstance();

        String yearPath = File.separator + cal.get(Calendar.YEAR);
        String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH));
        String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));

        makeDir(uploadPath, yearPath, monthPath, datePath);

        logger.info(datePath);

        return datePath;
    }

    private static void makeDir(String uploadPath, String... paths) {

        System.out.println(paths[paths.length - 1] + " : " + new File(paths[paths.length - 1]).exists());
        if (new File(paths[paths.length - 1]).exists()) {
            return;
        }

        for (String path : paths) {
            File dirPath = new File(uploadPath + path);

            if (!dirPath.exists()) {
                dirPath.mkdir();
            }
        }
    }

    private static String makeFilePath(String uploadPath, String path, String fileName) throws IOException {
        String filePath = uploadPath + path + File.separator + fileName;
        return filePath.substring(uploadPath.length()).replace(File.separatorChar, '/');
    }
}
