package com.echo.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Gaoxu
 * @Date: 2021/03/09 12:39
 * @Description: com.echo.oss.service.impl
 * @Version: 1.0
 */
public interface FileService {
    String uploadFile(MultipartFile file);
}
