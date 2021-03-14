package com.echo.eduservice.mapper;

import com.echo.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.echo.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Gaoxu
 * @since 2021-03-10
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CoursePublishVo getCoursePublishInfo(String courseId);

    void coursePublish(String courseId);
}
