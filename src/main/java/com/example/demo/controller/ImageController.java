package com.example.demo.controller;

import com.example.demo.common.Common;
import com.example.demo.config.StorageConfig;
import com.example.demo.service.ImageService;
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

    @Autowired
    ImageService imageService;

    private ResponseEntity<byte[]> getImageResponseEntity(Path path) {
        byte[] image = imageService.getImage(path);
        if (image != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // 设置响应的Content-Type为image/jpeg
            return new ResponseEntity<>(image, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get_clothes_image")
    public ResponseEntity<byte[]> getClothesImage(@RequestParam String userId, @RequestParam int clothesId) {
        Path path = Paths.get(storageConfig.getBasePath())
                .resolve(Common.USER + userId)
                .resolve(Common.CLOTHES_DIR)
                .resolve(clothesId + Common.JPG);

        return getImageResponseEntity(path);
    }


    @GetMapping("/get_suit_image")
    public ResponseEntity<byte[]> getSuitImage(@RequestParam String userId, @RequestParam int suitId) {
        Path path = Paths.get(storageConfig.getBasePath())
                .resolve(userId)
                .resolve(Common.SUIT_DIR)
                .resolve(suitId + Common.JPG);

        return getImageResponseEntity(path);
    }
//
//    @PostMapping("/upload_clothes_image")
//    public String uploadClothesImage(@RequestParam("image") MultipartFile image, @RequestParam("userId") int id) {
//
//    }

}
