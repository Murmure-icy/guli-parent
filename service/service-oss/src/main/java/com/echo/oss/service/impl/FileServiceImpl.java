package com.echo.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.echo.baseservice.exception.GuliException;
import com.echo.oss.service.FileService;
import com.echo.oss.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Author: Gaoxu
 * @Date: 2021/03/09 12:39
 * @Description: com.echo.oss.service.impl
 * @Version: 1.0
 */
@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadFile(MultipartFile file) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ConstantPropertiesUtil.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        String fileName = file.getOriginalFilename();
        //优化文件名，防止重复
        fileName = UUID.randomUUID().toString()+ "-" + fileName;
        //优化文件存储路径
        String s = new DateTime().toString("yyyy/MM/dd");
        fileName = s + "/" + fileName;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        try {
            InputStream is = file.getInputStream();
            // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName, fileName, is);

            String url = "https://" + bucketName + "." + endpoint + "/" + fileName;
            System.out.println("URL:" + url);
            // 关闭OSSClient。
            ossClient.shutdown();
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            throw new GuliException(20001,"上传失败");
        }
    }
}
