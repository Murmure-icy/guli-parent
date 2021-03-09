package com.echo.eduservice.controller;


import com.echo.commonutils.Result;
import com.echo.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Gaoxu
 * @since 2021-03-09
 */
@Api(description = "课程分类管理")
@RestController
@RequestMapping("/edu/subject")
@CrossOrigin
public class EduSubjectController {

    private EduSubjectService subjectService;

    @Autowired
    public void setSubjectService(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @ApiOperation("通过Excel添加课程分类")
    @PutMapping("/add")
    public Result addSubjectByExcel(MultipartFile file){
        subjectService.addSubjectByExcel(file,subjectService);
        return Result.ok();
    }
}

