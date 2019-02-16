package com.spring.yagaza.web.service;

import com.spring.yagaza.web.domain.ImgFile;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    ImgFile store(MultipartFile file) throws Exception;

    ImgFile load(Long id) throws Exception;

}
