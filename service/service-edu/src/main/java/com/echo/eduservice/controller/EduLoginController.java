package com.echo.eduservice.controller;

import com.echo.commonutils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Gaoxu
 * @Date: 2021/03/08 9:53
 * @Description: com.echo.eduservice.controller
 * @Version: 1.0
 */

@Api(description = "模拟登录")
@RestController
@RequestMapping("/edu")
@CrossOrigin
public class EduLoginController {

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(){
        return Result.ok().data("token","admin");
    }

    @ApiOperation("用户信息")
    @GetMapping("/info")
    public Result info(){
        return Result.ok()
                .data("roles","admin")
                .data("name","admin")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
