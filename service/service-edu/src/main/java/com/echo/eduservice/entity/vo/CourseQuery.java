package com.echo.eduservice.entity.vo;

import lombok.Data;

/**
 * @Author: Gaoxu
 * @Date: 2021/03/14 20:36
 * @Description: com.echo.eduservice.entity.vo
 * @Version: 1.0
 */
@Data
public class CourseQuery {
    private String title;
    private String teacherId;
    private String subjectParentId;
    private String subjectId;
}
