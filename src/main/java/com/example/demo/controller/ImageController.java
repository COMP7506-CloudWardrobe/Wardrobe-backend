package com.example.demo.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ImageController {

    @GetMapping("/clothes_images/{userId}/{clothesId}")
    public ResponseEntity<byte[]> getClothes(@PathVariable String userId, @PathVariable int clothesId) throws IOException {
        String imagePath = "/Users/celiaf/Documents/HKU/SAD/backend/images/%s/clothes/%d.jpg".formatted(userId,clothesId);
        Path path = Paths.get(imagePath);
        if (Files.exists(path)) {
            byte[] imageBytes = Files.readAllBytes(path);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // 设置响应的Content-Type为image/jpeg
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
