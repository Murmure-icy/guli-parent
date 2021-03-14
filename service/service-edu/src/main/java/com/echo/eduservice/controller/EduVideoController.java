package com.echo.eduservice.controller;


import com.echo.commonutils.Result;
import com.echo.eduservice.entity.EduChapter;
import com.echo.eduservice.entity.EduVideo;
import com.echo.eduservice.entity.vo.ChapterVo;
import com.echo.eduservice.service.EduChapterService;
import com.echo.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Gaoxu
 * @since 2021-03-12
 */
@Api(description = "小节管理")
@RestController
@RequestMapping("/edu/video")
@CrossOrigin
public class EduVideoController {
    private EduVideoService videoService;

    @Autowired
    public void setVideoService(EduVideoService videoService) {
        this.videoService = videoService;
    }

    @ApiOperation("根据ID获取章节信息")
    @GetMapping("/getById/{videoId}")
    public Result getById(
            @ApiParam(name = "videoId", value = "小节ID", required = true)
            @PathVariable String videoId){
        EduVideo video = videoService.getById(videoId);
        return Result.ok().data("video",video);
    }

    @ApiOperation("添加小节信息")
    @PostMapping("/add")
    public Result addVideo(
            @ApiParam(name = "video", value = "小节信息", required = true)
            @RequestBody EduVideo video){
        videoService.save(video);
        return Result.ok();
    }

    @ApiOperation("删除小节信息")
    @DeleteMapping("/delete/{videoId}")
    public Result deleteVideoById(
            @ApiParam(name = "videoId", value = "小节ID", required = true)
            @PathVariable String videoId){
        videoService.removeById(videoId);
        return Result.ok();
    }

    @ApiOperation("修改小节信息")
    @PostMapping("/update")
    public Result updateVideo(
            @ApiParam(name = "video", value = "小节信息", required = true)
            @RequestBody EduVideo video){
        videoService.updateById(video);
        return Result.ok();
    }
}

