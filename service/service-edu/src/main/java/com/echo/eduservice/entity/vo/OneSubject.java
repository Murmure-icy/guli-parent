package com.echo.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Gaoxu
 * @Date: 2021/03/10 13:22
 * @Description: com.echo.eduservice.entity.vo
 * @Version: 1.0
 */
@Data
public class OneSubject {
    @ApiModelProperty(value="课程类别ID")
    private String id;
    @ApiModelProperty(value="课程类别名称")
    private String title;
    private List<TwoSubject> children = new ArrayList<>();
}
