package com.spring.yagaza.web.repository;

import com.spring.yagaza.web.domain.ImgFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<ImgFile, Long> {
    //int saveFile(ImgFile file);

    //ImgFile findByFileNo(int fileNo);
}
