package com.example.blog.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/blog")
public class ImageUploadController {

    private static final String UPLOAD_DIR = "/opt/blog/uploads/";

    @PostMapping("/upload-image")
    public String handleImageUpload(@RequestParam("image") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String fileName = file.getOriginalFilename();
                String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
                if (!extension.equalsIgnoreCase("png") && !extension.equalsIgnoreCase("jpg") && !extension.equalsIgnoreCase("jpeg")) {
                    return "Only PNG, JPG and JPEG images are allowed.";
                }
                Path path = Paths.get(UPLOAD_DIR + fileName);
                Files.write(path, bytes);
                return "Image uploaded successfully: " + fileName;
            } catch (IOException e) {
                e.printStackTrace();
                return "Failed to upload image: " + file.getOriginalFilename();
            }
        } else {
            return "Failed to upload image because the file was empty.";
        }
    }
}
