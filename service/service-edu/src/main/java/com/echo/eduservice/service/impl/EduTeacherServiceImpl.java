package com.echo.eduservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.eduservice.entity.EduTeacher;
import com.echo.eduservice.entity.vo.TeacherQuery;
import com.echo.eduservice.mapper.EduTeacherMapper;
import com.echo.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Gaoxu
 * @since 2021-03-03
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery) {

    }
}
