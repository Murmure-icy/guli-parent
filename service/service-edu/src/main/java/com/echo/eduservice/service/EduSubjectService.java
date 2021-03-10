package com.echo.eduservice.service;

import com.echo.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.echo.eduservice.entity.vo.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Gaoxu
 * @since 2021-03-09
 */
public interface EduSubjectService extends IService<EduSubject> {

    void addSubjectByExcel(MultipartFile file, EduSubjectService subjectService);

    List<OneSubject> getAllSubject();
}
