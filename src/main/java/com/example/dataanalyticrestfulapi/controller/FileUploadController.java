package com.example.dataanalyticrestfulapi.controller;

import com.example.dataanalyticrestfulapi.model.response.FileResponse;
import com.example.dataanalyticrestfulapi.model.response.FileUploadResponse;
import com.example.dataanalyticrestfulapi.service.FileUploadService;
import com.example.dataanalyticrestfulapi.utils.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
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

    // allowed extension (jpg png...)
    private final List<String> ALLOWED_EXTENSIONS = List.of("jpg","png","jpeg");

    private final long MAX_FILE_SIZE = 1024 * 1024 * 5; // 5MB
    @PostMapping("/upload")
    public Response<FileResponse> fileUpload(@RequestParam("file") MultipartFile file){

            FileResponse response = uploadFile(file);
            return Response.<FileResponse>ok().setPayload(response).setMessage("Successfully to upload files");

    }

    // helper method
    @PostMapping("/mutiple-file-uplaod")
    public Response<List<FileResponse>> uploadMultipleFiles (@RequestParam("files") MultipartFile[] files) {

            List<FileResponse> responses = Arrays.stream(files)
                    .map(file -> uploadFile(file)).toList();
            return Response.<List<FileResponse>>ok().setPayload(responses)
                    .setMessage("Successfully upload multiple files ");

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

    @GetMapping("/download-file/{filename}")
    public ResponseEntity<?> dowloadFile(@PathVariable String filename, HttpServletRequest request) throws Exception{
        Resource resource = fileUploadService.loadFileAsResource(filename);
        // Try to determine file content
        String contentType = null;
        try{
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch (IOException exception){
            System.out.println("Error Getting Content type is :" + exception.getMessage());
        }
        if(contentType==null){
            contentType="application/octet-stream";
        }
        return ResponseEntity.ok().
                contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+ resource.getFilename()+"\"")
                .body(resource);
    }

    private FileResponse uploadFile(MultipartFile file){
        // file empty
        if(file.isEmpty())
            throw new IllegalArgumentException("files cannot be empty");
        // file size
        if(file.getSize()>MAX_FILE_SIZE)
            throw new MaxUploadSizeExceededException(MAX_FILE_SIZE);
        //extensions
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        if(!ALLOWED_EXTENSIONS.contains(extension.toLowerCase())){
            throw new IllegalArgumentException("Allow Extension are 'jpg' ,'png','jpeg' ");
        }

        String filename = fileUploadService.uploadImage(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file-service/download-file/")
                .path(filename)
                .toUriString();
        return new FileResponse()
                .setFilename(filename)
                .setFileDownloadUri(fileDownloadUri)
                .setFileType(file.getContentType())
                .setSize(file.getSize());
    }

}
