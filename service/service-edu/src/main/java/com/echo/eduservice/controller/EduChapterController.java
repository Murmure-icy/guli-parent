package com.echo.eduservice.controller;


import com.echo.commonutils.Result;
import com.echo.eduservice.entity.EduChapter;
import com.echo.eduservice.entity.vo.ChapterVo;
import com.echo.eduservice.service.EduChapterService;
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
 * @since 2021-03-12
 */
@Api(description = "章节管理")
@RestController
@RequestMapping("/edu/chapter")
@CrossOrigin
public class EduChapterController {
    private EduChapterService chapterService;

    @Autowired
    public void setChapterService(EduChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @ApiOperation("根据课程ID获取所有章节信息")
    @GetMapping("/getAllByCourseId/{courseId}")
    public Result getAllByCourseId(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId){
        List<ChapterVo> chapterList = chapterService.getChaptersByCourseId(courseId);
        return Result.ok().data("chapterList",chapterList);
    }

    @ApiOperation("根据ID获取章节信息")
    @GetMapping("/getById/{chapterId}")
    public Result getById(
            @ApiParam(name = "chapterId", value = "小节ID", required = true)
            @PathVariable String chapterId){
        EduChapter chapter = chapterService.getById(chapterId);
        return Result.ok().data("chapter",chapter);
    }

    @ApiOperation("添加章节信息")
    @PostMapping("/add")
    public Result addChapter(
            @ApiParam(name = "chapter", value = "章节信息", required = true)
            @RequestBody EduChapter chapter){
        chapterService.save(chapter);
        return Result.ok();
    }

    @ApiOperation("删除章节信息")
    @DeleteMapping("/delete/{chapterId}")
    public Result deleteChapterById(
            @ApiParam(name = "chapterId", value = "章节ID", required = true)
            @PathVariable String chapterId){
        chapterService.removeById(chapterId);
        return Result.ok();
    }

    @ApiOperation("修改章节信息")
    @PostMapping("/update")
    public Result updateChapter(
            @ApiParam(name = "chapter", value = "章节信息", required = true)
            @RequestBody EduChapter chapter){
        chapterService.updateById(chapter);
        return Result.ok();
    }
}

