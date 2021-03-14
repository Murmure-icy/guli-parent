package com.echo.eduservice.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Gaoxu
 * @Date: 2021/03/12 11:08
 * @Description: com.echo.eduservice.entity.vo
 * @Version: 1.0
 */
@Data
public class ChapterVo {

    private String id;

    private String title;

    private List<VideoVo> children =new ArrayList<>();

}
