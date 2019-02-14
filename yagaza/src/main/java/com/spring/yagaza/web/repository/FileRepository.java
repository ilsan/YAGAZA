package com.spring.yagaza.web.repository;

import com.spring.yagaza.web.domain.ImgFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface FileRepository extends JpaRepository<ImgFile, Long> {
    //int saveFile(ImgFile file);

    //ImgFile findByFileNo(int fileNo);
}
