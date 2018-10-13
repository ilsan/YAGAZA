package com.spring.yagaza.web.repository;

import com.spring.yagaza.web.domain.ImgFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FileRepository {
    int saveFile(ImgFile file);
    ImgFile findByFileNo(int fileNo);
}
