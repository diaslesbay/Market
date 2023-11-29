package com.example.test.service.impl;

import com.example.test.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageServiceImpl {
    String upload(MultipartFile image) throws IOException;
}
