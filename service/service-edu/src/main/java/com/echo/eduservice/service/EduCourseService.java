package com.echo.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.echo.eduservice.entity.vo.CoursePublishVo;
import com.echo.eduservice.entity.vo.CourseQuery;
import com.echo.eduservice.entity.vo.EduCourseInfo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Gaoxu
 * @since 2021-03-10
 */
public interface EduCourseService extends IService<EduCourse> {

    String insertCourseInfo(EduCourseInfo courseInfo);

    EduCourseInfo getCourseById(String courseId);

    void updateCourseInfo(EduCourseInfo courseInfo);

    CoursePublishVo getCoursePublishInfo(String courseId);

    void coursePublish(String courseId);

    List<EduCourse> getAllCourses();

    void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery);
}
