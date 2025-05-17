package com.pzj.schoolrun.controller;

import com.pzj.schoolrun.model.Result;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    private final COSClient cosClient;
    private final String bucketName;
    private final String baseUrl;

    @Autowired
    public FileUploadController(
            @Value("${tencent.cos.secretId}") String secretId,
            @Value("${tencent.cos.secretKey}") String secretKey,
            @Value("${tencent.cos.region}") String region,
            @Value("${tencent.cos.bucketName}") String bucketName,
            @Value("${tencent.cos.baseUrl}") String baseUrl) {

        // 初始化COS客户端
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        this.cosClient = new COSClient(cred, clientConfig);
        this.bucketName = bucketName;
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
    }

    @PostMapping("/upload")
    public Result<?> uploadImage(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return Result.error("上传文件不能为空");
        }

        File tempFile = null;
        try {
            // 生成唯一文件名并创建临时文件
            String fileName = generateUniqueFileName(file.getOriginalFilename());
            tempFile = createTempFile(file);

            // 上传到COS
            String cosKey = "uploads/" + fileName;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, cosKey, tempFile);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);

            // 返回文件访问URL
            return Result.success(baseUrl + cosKey);

        } catch (CosServiceException e) {
            // COS服务端异常
            return Result.error("COS服务异常: " + e.getErrorMessage());
        } catch (CosClientException e) {
            // 客户端异常
            return Result.error("上传失败: " + e.getMessage());
        } catch (IOException e) {
            // 文件操作异常
            return Result.error("文件处理失败: " + e.getMessage());
        } finally {
            // 确保删除临时文件
            if (tempFile != null && tempFile.exists()) {
                try {
                    Files.delete(tempFile.toPath());
                } catch (IOException e) {
                    // 日志记录删除失败
                }
            }
        }
    }

    private String generateUniqueFileName(String originalFilename) {
        return UUID.randomUUID().toString() + "_" +
                (originalFilename != null ? originalFilename : "file");
    }

    private File createTempFile(MultipartFile file) throws IOException {
        File tempFile = Files.createTempFile("upload-", ".tmp").toFile();
        file.transferTo(tempFile);
        return tempFile;
    }

    // 应用关闭时关闭COS客户端
    @Override
    protected void finalize() throws Throwable {
        try {
            if (cosClient != null) {
                cosClient.shutdown();
            }
        } finally {
            super.finalize();
        }
    }
}