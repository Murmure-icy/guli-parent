package com.echo.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.echo.baseservice.exception.GuliException;
import com.echo.eduservice.entity.EduSubject;
import com.echo.eduservice.entity.vo.ExcelSubjectData;
import com.echo.eduservice.entity.vo.OneSubject;
import com.echo.eduservice.entity.vo.TwoSubject;
import com.echo.eduservice.listener.SubjectExcelListener;
import com.echo.eduservice.mapper.EduSubjectMapper;
import com.echo.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Gaoxu
 * @since 2021-03-09
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void addSubjectByExcel(MultipartFile file, EduSubjectService subjectService) {
        try {
            InputStream is = file.getInputStream();
            EasyExcel.read(is, ExcelSubjectData.class,new SubjectExcelListener(subjectService))
                    .sheet()
                    .doRead();
        } catch (IOException e) {
//            e.printStackTrace();
            throw new GuliException(20001,"添加课程分类失败");
        }
    }

    @Override
    public List<OneSubject> getAllSubject() {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",0);
        List<EduSubject> list = list(wrapper);
        List<OneSubject> oneSubjects = new ArrayList<>();
        for (EduSubject eduSubject : list) {
            OneSubject oneSubject = new OneSubject();
            oneSubject.setId(eduSubject.getId());
            oneSubject.setTitle(eduSubject.getTitle());
            oneSubjects.add(oneSubject);
        }
        for (OneSubject oneSubject : oneSubjects) {
            wrapper = new QueryWrapper<>();
            wrapper.eq("parent_id",oneSubject.getId());
            List<EduSubject> list2 = list(wrapper);
            List<TwoSubject> twoSubjects = new ArrayList<>();
            for (EduSubject eduSubject : list2) {
                TwoSubject twoSubject = new TwoSubject();
                twoSubject.setId(eduSubject.getId());
                twoSubject.setTitle(eduSubject.getTitle());
                twoSubjects.add(twoSubject);
            }
            oneSubject.setChildren(twoSubjects);
        }
        return oneSubjects;
    }
}
