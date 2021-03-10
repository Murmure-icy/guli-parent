package com.echo.eduservice.controller;

import com.echo.commonutils.Result;
import com.echo.eduservice.entity.vo.OneSubject;
import com.echo.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    @PostMapping("/add")
    public Result addSubjectByExcel(MultipartFile file){
        subjectService.addSubjectByExcel(file,subjectService);
        return Result.ok();
    }

    @ApiOperation("查询所有课程分类信息")
    @GetMapping("/getAll")
    public Result getAllSubjects(){
        List<OneSubject> subjects = subjectService.getAllSubject();
        return Result.ok().data("subjects",subjects);
    }
}

