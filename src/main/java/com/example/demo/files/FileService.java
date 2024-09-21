package com.example.demo.files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    @Value("${upload.images}")
    private String uploadDirectory;

    public void save(MultipartFile request){
        try {
            File directory = new File(uploadDirectory);
            if(!directory.exists()){
                directory.mkdir();
            }
            byte[] bytes = request.getBytes();
            Path path = Paths.get(uploadDirectory + request.getOriginalFilename());
            Files.write(path,bytes);

        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }
            throw new RuntimeException(e.getMessage());
        }
    }
    public String getDirectory() {
        return this.uploadDirectory;
    }
}
