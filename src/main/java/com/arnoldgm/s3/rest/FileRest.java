package com.arnoldgm.s3.rest;

import com.amazonaws.util.IOUtils;
import com.arnoldgm.s3.aws.S3Manager;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileRest {

    private S3Manager s3Manager;


    public FileRest(S3Manager s3Manager) {
        this.s3Manager = s3Manager;
    }

    @PostMapping("/file/image")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] image = IOUtils.toByteArray(file.getInputStream());
        return s3Manager.store(image);
    }

    @GetMapping(value = "/file/image/{filename}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] downloadImage(@PathVariable String filename) throws IOException {
        return s3Manager.get(filename);
    }
}
