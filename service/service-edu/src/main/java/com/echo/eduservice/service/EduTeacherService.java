package com.echo.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.echo.eduservice.entity.vo.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Gaoxu
 * @since 2021-03-03
 */
public interface EduTeacherService extends IService<EduTeacher> {
    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);
}
