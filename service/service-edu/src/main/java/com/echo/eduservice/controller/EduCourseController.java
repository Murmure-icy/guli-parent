package com.echo.eduservice.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.commonutils.Result;
import com.echo.eduservice.entity.EduCourse;
import com.echo.eduservice.entity.vo.CoursePublishVo;
import com.echo.eduservice.entity.vo.CourseQuery;
import com.echo.eduservice.entity.vo.EduCourseInfo;
import com.echo.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        String courseId = courseService.insertCourseInfo(courseInfo);
        return Result.ok().data("courseId",courseId);
    }

    @ApiOperation("添加课程信息")
    @PostMapping("/update")
    public Result updateCourseInfo(@RequestBody EduCourseInfo courseInfo){
        courseService.updateCourseInfo(courseInfo);
        return Result.ok();
    }

    @ApiOperation("根据课程ID查询课程信息")
    @GetMapping("/getById")
    public Result getCourseInfoById(String courseId){
        EduCourseInfo courseInfo = courseService.getCourseById(courseId);
        return Result.ok().data("courseInfo",courseInfo);
    }

    @ApiOperation("查询所有课程信息")
    @GetMapping("/getAllCourse")
    public Result getAllCourse(){
        List<EduCourse> courses = courseService.getAllCourses();
        return Result.ok().data("courses",courses);
    }

    @ApiOperation("课程列表-分页-条件查询")
    @PostMapping("/paging/condition/{page}/{limit}")
    public Result pagingByCondition(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable("page") Integer page,
            @ApiParam(name = "limit", value = "每页条数", required = true)
            @PathVariable("limit") Integer limit,
            @RequestBody CourseQuery courseQuery){

        Page<EduCourse> pageParam = new Page<>(page, limit);

        courseService.pageQuery(pageParam, courseQuery);
        List<EduCourse> records = pageParam.getRecords();

        long total = pageParam.getTotal();

        return  Result.ok().data("total", total).data("courses", records);
    }

    @ApiOperation("查询最终发布课程信息")
    @GetMapping("/getCoursePublishInfo/{courseId}")
    public Result getCoursePublishInfo(@PathVariable String courseId){
        CoursePublishVo coursePublishInfo = courseService.getCoursePublishInfo(courseId);
        return Result.ok().data("coursePublishInfo",coursePublishInfo);
    }

    @ApiOperation("发布课程")
    @PostMapping("/coursePublish/{courseId}")
    public Result coursePublish(@PathVariable String courseId){
        courseService.coursePublish(courseId);
        return Result.ok();
    }

    @ApiOperation("根据课程ID删除课程")
    @DeleteMapping("/delete/{courseId}")
    public Result deleteCourseById(@PathVariable String courseId){
        courseService.removeById(courseId);
        return Result.ok();
    }
}

