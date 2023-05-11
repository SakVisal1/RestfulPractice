package com.example.dataanalyticrestfulapi.service.ServiceImpl;

import com.example.dataanalyticrestfulapi.service.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {


    private final String serverLocation = "src/main/resources/image/";
    Path fileLocationStorage;
    public FileUploadServiceImpl(){
        fileLocationStorage = Paths.get("src/main/resources/image/");
      try{
          if (!Files.exists(fileLocationStorage)){
            Files.createDirectories(fileLocationStorage);
          } else {
              System.out.println("Directory is ready existed !!");
          }
      }catch (Exception ex){
          System.out.println("Error Creating directory : " + ex.getMessage());
      }
    }
    @Override
    public String uploadImage( MultipartFile file)  {
    // format file name
     // cute image.png
     String filename = file.getOriginalFilename();
     //check to see if file is empty
     String[] fileCompartments = filename.split("\\.");

     // String[]
     // [0] = 35535356-qadgtyts-ytwycvvdj
     //[1] = png

     filename = UUID.randomUUID()+"."+fileCompartments[1];
     //a2342sdfgs-asdthgdgjshj.png

     Path reslovedPath = fileLocationStorage.resolve(filename);
        try {
            Files.copy(file.getInputStream(),reslovedPath, StandardCopyOption.REPLACE_EXISTING);
            return filename;
       }catch (Exception ex) {
            return ex.getMessage();
        }
    }

    @Override
    public String deleteFileByName(String filename) {
        Path imageLocation = Paths.get(serverLocation);
        List<File> allFiles = List.of(imageLocation.toFile().listFiles());

        // filter file that we going to delete
        File deleteFile = allFiles.stream().filter(file-> file.getName().equals(filename))
                .findFirst().orElse(null);

    try{
        if(deleteFile!=null){
            Files.delete(deleteFile.toPath());
            return "Delete file successfully";
        }else

            return "file with"+filename+"doesn't exit";

    }catch (Exception e){
        System.out.println("Error delete file by name :" +e.getMessage());
        return "Exception occurred ! failed to delete file by name";
    }
    }

    @Override
    public String deleteAllFiles() {
        // define location to delete file
        Path imagesLocation = Paths.get(serverLocation);
        File[] files = imagesLocation.toFile().listFiles();

        try{

            if(files == null || files.length == 0){
                return "There is no files to delete !!";
            }
            for(File file : files){
                Files.delete(file.toPath());
            }
            return "Successfully delete all files";
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Exception Delete all files :"+ex.getMessage());
            return "Failed to delete all files ! Exception occurred !";
        }

    }
}
