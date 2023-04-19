package com.example.demo.controller;

import com.example.demo.common.StringCommon;
import com.example.demo.config.StorageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@RestController
public class ImageController {

    @Autowired
    StorageConfig storageConfig;

    @GetMapping("/get_clothes_images")
    public ResponseEntity<byte[]> getClothes(@RequestParam String userId, @RequestParam int clothesId) throws IOException {
        Path path = Paths.get(storageConfig.getBasePath())
                .resolve(userId)
                .resolve(storageConfig.getClothesPath())
                .resolve(clothesId + StringCommon.JPG);

        if (Files.exists(path)) {
            byte[] imageBytes = Files.readAllBytes(path);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // 设置响应的Content-Type为image/jpeg
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("image") MultipartFile image) {

        // 处理上传的文件
        // 获取上传的文件名
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
        System.out.println(fileName);

        // 检查文件名是否合法
        if (fileName.contains("..")) {
            throw new RuntimeException("Invalid file name: " + fileName);
        }

        try {
            // 创建保存文件的路径
            Path targetPath = Paths.get(storageConfig.getBasePath()).resolve(fileName);

            // 保存文件到本地磁盘
            Files.copy(image.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            // 返回保存文件的路径
            return targetPath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + fileName, e);
        }
    }

}
