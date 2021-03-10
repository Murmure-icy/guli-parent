package com.echo.eduservice.service.impl;

import com.echo.baseservice.exception.GuliException;
import com.echo.eduservice.entity.EduCourse;
import com.echo.eduservice.entity.EduCourseDescription;
import com.echo.eduservice.entity.vo.EduCourseInfo;
import com.echo.eduservice.mapper.EduCourseMapper;
import com.echo.eduservice.service.EduCourseDescriptionService;
import com.echo.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Gaoxu
 * @since 2021-03-10
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    private EduCourseDescriptionService courseDescriptionService;

    @Autowired
    public void setCourseDescriptionService(EduCourseDescriptionService courseDescriptionService) {
        this.courseDescriptionService = courseDescriptionService;
    }

    @Override
    public void insertCourseInfo(EduCourseInfo courseInfo) {
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfo,course);
        boolean isSave = save(course);
        if (!isSave){
            throw new GuliException(20001,"添加课程失败");
        }
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setId(course.getId());
        courseDescription.setDescription(courseInfo.getDescription());
        courseDescriptionService.save(courseDescription);
    }
}
