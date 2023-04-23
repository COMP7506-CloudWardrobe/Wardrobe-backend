package com.example.demo.service.Impl;

import com.example.demo.common.Common;
import com.example.demo.config.StorageConfig;
import com.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    StorageConfig storageConfig;


    @Override
    public byte[] getImage(Path path) {
        try {
            if (Files.exists(path)) return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
//    @Transactional
    public boolean saveImage(Path path, MultipartFile image) throws IOException {
        Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//        throw new IOException();
        return true;
    }

    @Override
    public boolean deleteImage(Path path) throws IOException {
        return new File(path.toUri()).delete();
    }
}

