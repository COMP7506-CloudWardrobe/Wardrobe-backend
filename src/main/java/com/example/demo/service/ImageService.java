package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface ImageService {
    byte[] getImage(Path path);

    boolean saveImage(Path path, MultipartFile image) throws IOException;

    boolean deleteImage(Path path) throws IOException;
}
