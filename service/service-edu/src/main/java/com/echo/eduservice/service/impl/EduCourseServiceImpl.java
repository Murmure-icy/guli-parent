package com.echo.eduservice.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.SelectPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.baseservice.exception.GuliException;
import com.echo.eduservice.entity.EduCourse;
import com.echo.eduservice.entity.EduCourseDescription;
import com.echo.eduservice.entity.vo.CoursePublishVo;
import com.echo.eduservice.entity.vo.CourseQuery;
import com.echo.eduservice.entity.vo.EduCourseInfo;
import com.echo.eduservice.mapper.EduCourseMapper;
import com.echo.eduservice.service.EduCourseDescriptionService;
import com.echo.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
//    private EduCourseMapper courseMapper;
    private EduCourseDescriptionService courseDescriptionService;

//    @Autowired
//    public void setEduCourseMapper(EduCourseMapper courseMapper) {
//        this.courseMapper = courseMapper;
//    }

    @Autowired
    public void setCourseDescriptionService(EduCourseDescriptionService courseDescriptionService) {
        this.courseDescriptionService = courseDescriptionService;
    }

    @Override
    public String insertCourseInfo(EduCourseInfo courseInfo) {
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
        return course.getId();
    }

    @Override
    public EduCourseInfo getCourseById(String courseId) {
        EduCourseInfo courseInfo = new EduCourseInfo();
        EduCourse course = getById(courseId);
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        BeanUtils.copyProperties(course,courseInfo);
        courseInfo.setDescription(courseDescription.getDescription());
        return courseInfo;
    }

    @Override
    public void updateCourseInfo(EduCourseInfo courseInfo) {
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfo,course);
        boolean isSave = updateById(course);
        if (!isSave){
            throw new GuliException(20001,"更新课程失败");
        }
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setId(course.getId());
        courseDescription.setDescription(courseInfo.getDescription());
        courseDescriptionService.updateById(courseDescription);
    }

    @Override
    public CoursePublishVo getCoursePublishInfo(String courseId) {
        CoursePublishVo coursePublish = baseMapper.getCoursePublishInfo(courseId);
        return coursePublish;
    }

    @Override
    public void coursePublish(String courseId) {
        baseMapper.coursePublish(courseId);
    }

    @Override
    public List<EduCourse> getAllCourses() {
        List<EduCourse> list = list(null);
        return list;
    }

    @Override
    public void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");
        if (courseQuery == null){
            baseMapper.selectPage(pageParam,wrapper);
            return;
        }

        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getTeacherId();
        String oneSubjectId = courseQuery.getSubjectParentId();
        String twoSubjectId = courseQuery.getSubjectId();

        if (!StringUtils.isEmpty(title)){
            wrapper.like("title",title);
        }
        if (!StringUtils.isEmpty(teacherId)){
            wrapper.eq("teacher_id",teacherId);
        }
        if (!StringUtils.isEmpty(oneSubjectId)){
            wrapper.eq("subject_parent_id",oneSubjectId);
        }
        if (!StringUtils.isEmpty(twoSubjectId)){
            wrapper.eq("subject_id",twoSubjectId);
        }
        baseMapper.selectPage(pageParam,wrapper);

    }
}
