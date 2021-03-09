package com.echo.eduservice.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author: Gaoxu
 * @Date: 2021/03/09 20:18
 * @Description: com.echo.eduservice.entity.vo
 * @Version: 1.0
 */
@Data
public class ExcelSubjectData {
    @ExcelProperty(index = 0)
    private String oneSubjectName;
    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
