package com.echo.eduservice.service;

import com.echo.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.echo.eduservice.entity.vo.EduCourseInfo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Gaoxu
 * @since 2021-03-10
 */
public interface EduCourseService extends IService<EduCourse> {

    void insertCourseInfo(EduCourseInfo courseInfo);
}
