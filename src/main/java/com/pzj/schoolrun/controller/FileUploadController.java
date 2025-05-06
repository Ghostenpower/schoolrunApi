package com.pzj.schoolrun.controller;

import com.pzj.schoolrun.model.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class FileUploadController {


    @PostMapping("/upload")
    public Result<?> uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("上传文件不能为空");
        }

        try {
            // 生成唯一文件名
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            // 指定文件存储路径（绝对路径）
            String uploadDir = Paths.get("").toAbsolutePath().toString() + "/uploads/";
            String filePath = uploadDir + fileName;
            // 创建目标文件
            File dest = new File(filePath);
            // 确保目录存在
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            // 保存文件
            file.transferTo(dest);

            // 返回文件访问路径
            String fileUrl = "http://localhost:8080/uploads/" + fileName;
            return Result.success(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("文件上传失败");
        }
    }

}
