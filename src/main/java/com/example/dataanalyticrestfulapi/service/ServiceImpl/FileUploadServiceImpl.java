package com.example.dataanalyticrestfulapi.service.ServiceImpl;

import com.example.dataanalyticrestfulapi.service.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        String randomID = UUID.randomUUID().toString();
        assert name != null;
        String filename = randomID.concat(name.substring(name.lastIndexOf(".")));

        String filePath = path + File.separator + filename;

        File f = new File(path);
        if (!f.exists()){
            f.mkdirs();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return name;
    }
}
