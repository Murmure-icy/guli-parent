package com.echo.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.echo.eduservice.entity.EduSubject;
import com.echo.eduservice.entity.vo.ExcelSubjectData;
import com.echo.eduservice.service.EduSubjectService;

/**
 * @Author: Gaoxu
 * @Date: 2021/03/09 20:17
 * @Description: com.echo.eduservice.listener
 * @Version: 1.0
 */
public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {
    private EduSubjectService subjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        if (excelSubjectData == null){
            return;
        }

        EduSubject oneSubject = existOneSubject(excelSubjectData.getOneSubjectName());
        if (oneSubject == null){
            oneSubject = new EduSubject();
            oneSubject.setTitle(excelSubjectData.getOneSubjectName());
            oneSubject.setParentId("0");
            oneSubject.setSort(1);

            subjectService.save(oneSubject);
        }

        String parentId = oneSubject.getParentId();

        EduSubject twoSubject = existTwoSubject(excelSubjectData.getTwoSubjectName(),parentId);
        if (twoSubject == null){
            twoSubject = new EduSubject();
            twoSubject.setTitle(excelSubjectData.getTwoSubjectName());
            twoSubject.setParentId(parentId);
            twoSubject.setSort(2);

            subjectService.save(twoSubject);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    /**
     * 判断一级课程在数据库是否存在
     * @param name 课程名称
     * @return
     */
    private EduSubject existOneSubject(String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name).eq("parent_id","0");
        EduSubject subject = subjectService.getOne(wrapper);
        return subject;
    }

    /**
     * 判断二级课程在数据库是否存在
     * @param name 课程名称
     * @param pid 父分类ID
     * @return
     */
    private EduSubject existTwoSubject(String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name).eq("parent_id",pid);
        EduSubject subject = subjectService.getOne(wrapper);
        return subject;
    }
}
