package com.example.dataanalyticrestfulapi.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {
    String uploadImage( MultipartFile file);
    String deleteFileByName(String filename);
    String deleteAllFiles();

    Resource loadFileAsResource(String filename) throws Exception;
}
