package com.arnoldgm.s3.aws;

import com.amazonaws.util.IOUtils;
import com.arnoldgm.s3.util.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Component
public class S3Manager {

    private ResourceLoader resourceLoader;
    private NameGenerator nameGenerator;
    private final Logger logger = LoggerFactory.getLogger(S3Manager.class);
    @Value("${app.bucketname}")
    private String BUCKET_NAME;

    public S3Manager(ResourceLoader resourceLoader, NameGenerator nameGenerator) {
        this.resourceLoader = resourceLoader;
        this.nameGenerator = nameGenerator;
    }

    public String store(byte[] image) {
        String filename = nameGenerator.getName();
        Resource resource = this.resourceLoader.getResource("s3://" + BUCKET_NAME + "/".concat(filename));
        WritableResource writableResource = (WritableResource) resource;
        try (OutputStream outputStream = writableResource.getOutputStream()) {
            outputStream.write(image);
        } catch (IOException e) {
            logger.error("File can't upload", e);
        }
        return filename;
    }

    public byte[] get(String filename) throws IOException {
        Resource resource = this.resourceLoader.getResource("s3://" + BUCKET_NAME + "/".concat(filename));
        InputStream inputStream;
        try {
            inputStream = resource.getInputStream();
        } catch (FileNotFoundException e) {
            logger.error("File not found", e);
            return new byte[0];
        }
        return IOUtils.toByteArray(inputStream);
    }
}
