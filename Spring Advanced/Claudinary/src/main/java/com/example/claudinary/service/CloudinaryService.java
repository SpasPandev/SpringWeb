package com.example.claudinary.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {

    CloudinaryImage upload(MultipartFile multipartFile) throws IOException;

    boolean delete(String publicId);
}