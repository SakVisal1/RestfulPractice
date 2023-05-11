package com.example.dataanalyticrestfulapi.controller;

import com.example.dataanalyticrestfulapi.model.response.FileResponse;
import com.example.dataanalyticrestfulapi.model.response.FileUploadResponse;
import com.example.dataanalyticrestfulapi.service.FileUploadService;
import com.example.dataanalyticrestfulapi.utils.Response;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/file-service")
public class FileUploadController {
    private final FileUploadService fileUploadService;
    FileUploadController(FileUploadService fileUploadService){
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/upload")
    public Response<FileResponse> fileUpload(@RequestParam("file") MultipartFile file){
        try {
            FileResponse response = uploadFile(file);
            return Response.<FileResponse>ok().setPayload(response).setMessage("Successfully to upload files");
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Failed to Upload images " + ex.getMessage());

            return Response.<FileResponse>exception().setMessage("Failed to upload an images:" +
                    "! Exception Occurred");
        }
    }

    // helper method
    @PostMapping("/mutiple-file-uplaod")
    public Response<List<FileResponse>> uploadMultipleFiles (@RequestParam("files") MultipartFile[] files) {
        try {
            List<FileResponse> responses = Arrays.stream(files)
                    .map(file -> uploadFile(file)).toList();
            return Response.<List<FileResponse>>ok().setPayload(responses)
                    .setMessage("Successfully upload multiple files ");
        } catch (Exception ex) {
            System.out.println("Exceptiom happened : " +ex.getMessage());
            return Response.<List<FileResponse>>exception().setMessage("failed to uplaod multiple files! " +
                    "Exception occurred" +
                    "");
        }
    }

    // for delete file single and multiple

    @DeleteMapping("/delete-file/{filename}")
    public String deleteSingleFile(@PathVariable String filename){

        String result = fileUploadService.deleteFileByName(filename);
        return result;
    }

    @DeleteMapping("/delete-all-files")
    public String deleteAllFiles(){
        String results = fileUploadService.deleteAllFiles();
        return results;
    }

    private FileResponse uploadFile(MultipartFile file){
        String filename = fileUploadService.uploadImage(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file-service/download-file")
                .path(filename)
                .toUriString();
        return new FileResponse()
                .setFilename(filename)
                .setFileDownloadUri(fileDownloadUri)
                .setFileType(file.getContentType())
                .setSize(file.getSize());
    }

}
