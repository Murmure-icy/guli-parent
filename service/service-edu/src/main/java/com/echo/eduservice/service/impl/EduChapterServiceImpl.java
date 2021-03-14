package com.echo.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.echo.eduservice.entity.EduChapter;
import com.echo.eduservice.entity.EduVideo;
import com.echo.eduservice.entity.vo.ChapterVo;
import com.echo.eduservice.entity.vo.VideoVo;
import com.echo.eduservice.mapper.EduChapterMapper;
import com.echo.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.echo.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Gaoxu
 * @since 2021-03-12
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    private EduVideoService videoService;

    @Autowired
    public void setVideoService(EduVideoService videoService) {
        this.videoService = videoService;
    }

    @Override
    public List<ChapterVo> getChaptersByCourseId(String courseId) {
        List<ChapterVo> chapters = new ArrayList<>();

        QueryWrapper<EduChapter> w1 = new QueryWrapper<>();
        w1.eq("course_id",courseId)
                .orderByAsc("sort", "id");
        List<EduChapter> chapterList = list(w1);
        for (EduChapter eduChapter : chapterList) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);

            QueryWrapper<EduVideo> w2 = new QueryWrapper<>();
            w2.eq("chapter_id",eduChapter.getId())
                    .eq("course_id", courseId)
                    .orderByAsc("sort", "id");
            List<EduVideo> list = videoService.list(w2);
            List<VideoVo> videoVoList = new ArrayList<>();
            for (EduVideo eduVideo : list) {
                VideoVo videoVo = new VideoVo();
                BeanUtils.copyProperties(eduVideo,videoVo);
                videoVoList.add(videoVo);
            }
            chapterVo.setChildren(videoVoList);
            chapters.add(chapterVo);
        }
        return chapters;
    }
}
