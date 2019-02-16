package com.spring.yagaza.web.service.Impl;

import com.spring.yagaza.web.domain.ImgFile;
import com.spring.yagaza.web.repository.FileRepository;
import com.spring.yagaza.web.service.FileService;
import com.spring.yagaza.web.util.FileUploadUtil;
import javafx.util.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
public class FileServiceImpl implements FileService {

    private FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public ImgFile store(MultipartFile file) throws Exception {

        String uploadPath = "C:\\STUDY\\temp\\dir";
        Path rootLocation = Paths.get(uploadPath);

        try {
            if (file.isEmpty()) {
                throw new Exception("Failed to store empty file " + file.getOriginalFilename());
            }

            String saveFileName = FileUploadUtil.fileSave(rootLocation.toString(), file);

            Resource resource = new UrlResource(Paths.get(uploadPath + File.separator + saveFileName).toUri());

            if (saveFileName.toCharArray()[0] == '/') {
                saveFileName = saveFileName.substring(1);
            }

            //Resource resource = loadAsResource(saveFileName);

            return fileRepository.save(
                    ImgFile.builder()
                            .fileSaveNm(saveFileName)
                            .fileOrgNm(file.getOriginalFilename())
                            .contentType(file.getContentType())
                            .fileDir(rootLocation.toString().replace(File.separatorChar, '/'))
                            .fileSize(resource.contentLength())
                            .fileCategory("1")
                            .regDate(LocalDateTime.now())
                            .build()
                    );
        } catch (IOException e) {
            throw new Exception("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public ImgFile load(Long id) {
        return fileRepository.getOne(id);
    }
}
