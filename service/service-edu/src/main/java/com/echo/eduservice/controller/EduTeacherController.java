package com.echo.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.baseservice.exception.GuliException;
import com.echo.commonutils.Result;
import com.echo.eduservice.entity.EduTeacher;
import com.echo.eduservice.entity.vo.TeacherQuery;
import com.echo.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Gaoxu
 * @since 2021-03-03
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/teacher")
public class EduTeacherController {

    private EduTeacherService teacherService;

    @Autowired
    public void setTeacherService(EduTeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/get/teachers")
    public Result list() {
        try {
            int i = 10 / 0;
        } catch (Exception e) {
            throw new GuliException(20001,"除数不能为0");
        }
        List<EduTeacher> list = teacherService.list(null);
        return Result.ok().data("teacherList", list);
    }

    @ApiOperation(value = "讲师列表-分页")
    @GetMapping("/paging/{page}/{limit}")
    public Result paging(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable("page") Integer page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable("limit") Integer limit) {
        //封装分页信息
        Page<EduTeacher> pageInfo = new Page<>(page, limit);
        List<EduTeacher> records = null;
        long total = 0;
        try {
            teacherService.page(pageInfo, null);
            //查询的讲师信息
            records = pageInfo.getRecords();
            //分页查询的信息条目
            total = pageInfo.getTotal();
        } catch (Exception e) {
            //e.printStackTrace();
            return Result.error();
        }
        return Result.ok().data("total", total).data("teacherListPage", records);
    }

    @ApiOperation("讲师列表-分页-条件查询")
    @PostMapping("/paging/condition/{page}/{limit}")
    public Result pagingByCondition(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable("page") Integer page,
            @ApiParam(name = "limit", value = "每页条数", required = true)
            @PathVariable("limit") Integer limit,
            @ApiParam(name = "teacherQuery", value = "分页条件封装对象", required = true)
            @RequestBody TeacherQuery teacherQuery) {
        //分页条件拆分
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        //封装查询条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }
        //分页对象
        Page<EduTeacher> pageInfo = new Page<>(page, limit);
        teacherService.page(pageInfo,wrapper);
        List<EduTeacher> teacherList = pageInfo.getRecords();
        long total = pageInfo.getTotal();

        return Result.ok().data("teacherList",teacherList).data("total",total);
    }

    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("/delete/{id}")
    public Result deleteTeacherById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable("id") String id) {
        boolean flag = teacherService.removeById(id);
        //判断是否删除成功
        if (!flag) {
            return Result.error();
        }
        return Result.ok();
    }

    @ApiOperation(value="添加讲师")
    @PostMapping("/add")
    public Result addTeacher(
            @ApiParam(name = "teacher",value="讲师对象",required = true)
            @RequestBody EduTeacher teacher){
        boolean success = teacherService.save(teacher);
        if (success){
            return Result.ok();
        }
        return Result.error();
    }

    @ApiOperation(value="修改讲师信息")
    @PutMapping("/update")
    public Result updateTeacher(
            @ApiParam(name="teacher",value="修改的讲师对象",required = true)
            @RequestBody EduTeacher teacher){
        boolean success = teacherService.updateById(teacher);
        if (success){
            return Result.ok();
        }
        return Result.error();
    }
}

