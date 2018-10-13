package com.spring.yagaza.web.service.Impl;

import com.spring.yagaza.web.domain.ImgFile;
import com.spring.yagaza.web.repository.FileRepository;
import com.spring.yagaza.web.service.FileService;
import com.spring.yagaza.web.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    public ImgFile store(MultipartFile file) throws Exception {

        String uploadPath = "C:\\STUDY\\temp\\dir";
        Path rootLocation = Paths.get(uploadPath);

        Resource resource = new UrlResource(Paths.get(uploadPath).toUri());

        try {
            if (file.isEmpty()) {
                throw new Exception("Failed to store empty file " + file.getOriginalFilename());
            }

            String saveFileName = FileUploadUtil.fileSave(rootLocation.toString(), file);

            if (saveFileName.toCharArray()[0] == '/') {
                saveFileName = saveFileName.substring(1);
            }

            //Resource resource = loadAsResource(saveFileName);

            ImgFile saveFile = new ImgFile();
            saveFile.setFileSaveNm(saveFileName);
            saveFile.setFileOrgNm(file.getOriginalFilename());
            saveFile.setContentType(file.getContentType());
            saveFile.setFileDir(rootLocation.toString().replace(File.separatorChar, '/'));
            saveFile.setFileSize(resource.contentLength());
            saveFile.setFileCategory("1");
            //saveFile.setRegDate(new Date());
            fileRepository.saveFile(saveFile);

            return saveFile;
        } catch (IOException e) {
            throw new Exception("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public ImgFile load(String id) throws Exception {
        ImgFile file = fileRepository.findByFileName(id);
        return file;
    }
}
