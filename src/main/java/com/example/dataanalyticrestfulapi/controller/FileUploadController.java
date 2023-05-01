package com.example.dataanalyticrestfulapi.controller;

import com.example.dataanalyticrestfulapi.model.response.FileUploadResponse;
import com.example.dataanalyticrestfulapi.service.FileUploadService;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@RestController
public class FileUploadController {
    private final FileUploadService fileUploadService;
    FileUploadController(FileUploadService fileUploadService){
        this.fileUploadService = fileUploadService;
    }
    @Value("images/")
    private String path;
    @PostMapping("/upload")
    public ResponseEntity<FileUploadResponse> fileUpload(@RequestParam("image") MultipartFile image){
        String filename = null;
        try {
            filename = this.fileUploadService.uploadImage(path, image);

        } catch (IOException e) {
            return new ResponseEntity<>(new FileUploadResponse(null, "images is not upload !!!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new FileUploadResponse(filename, "images is successfully uploaded to Server!!"), HttpStatus.OK);
    }
}
