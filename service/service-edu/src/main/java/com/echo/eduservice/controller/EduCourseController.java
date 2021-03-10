package com.echo.eduservice.controller;


import com.echo.commonutils.Result;
import com.echo.eduservice.entity.vo.EduCourseInfo;
import com.echo.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Gaoxu
 * @since 2021-03-10
 */
@Api(description = "课程管理")
@RestController
@RequestMapping("/edu/course")
@CrossOrigin
public class EduCourseController {

    private EduCourseService courseService;

    @Autowired
    public void setCourseService(EduCourseService courseService) {
        this.courseService = courseService;
    }

    @ApiOperation("添加课程信息")
    @PostMapping("/add")
    public Result addCourseInfo(@RequestBody EduCourseInfo courseInfo){
        courseService.insertCourseInfo(courseInfo);
        return Result.ok();
    }
}

