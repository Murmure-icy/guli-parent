package com.echo.oss.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.echo.commonutils.Result;
import com.echo.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Gaoxu
 * @Date: 2021/03/09 12:37
 * @Description: com.echo.oss.controller
 * @Version: 1.0
 */
@Api(description="文件管理")
@RestController
@RequestMapping("/ossservice/fileoss")
@CrossOrigin
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "上传文件")
    @PostMapping("uploadFile")
    public Result uploadFile(MultipartFile file){
        //1获取文件
        //2调用接口上传文件，获取Url
        String url = fileService.uploadFile(file);
        return Result.ok().data("url",url);
    }

}
