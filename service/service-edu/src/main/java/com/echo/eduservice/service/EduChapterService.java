package com.echo.eduservice.service;

import com.echo.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.echo.eduservice.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Gaoxu
 * @since 2021-03-12
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChaptersByCourseId(String courseId);
}
