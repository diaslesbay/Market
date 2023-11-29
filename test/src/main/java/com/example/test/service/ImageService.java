package com.example.test.service;

import com.example.test.config.properties.MinioProperties;
import com.example.test.dto.ProductDto;
import com.example.test.service.impl.ImageServiceImpl;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static org.hibernate.mapping.Constraint.generateName;

@Service
@RequiredArgsConstructor
public class ImageService implements ImageServiceImpl {
    private final MinioClient minioClient;
    private final MinioProperties minioProperties;


    @Override
    public String upload(MultipartFile image) throws IOException {
        createBucket();

        if(image.isEmpty() || image.getOriginalFilename() == null){
            throw new RuntimeException("Image must have name");
        }

        String fileName = generateFileName(image);
        InputStream inputStream;
        inputStream = image.getInputStream();
        saveImage(inputStream, fileName);
        return fileName;
    }
    @SneakyThrows
    private void createBucket() {
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(minioProperties.getBucket())
                .build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(minioProperties.getBucket())
                    .build());
        }
    }

    private String generateFileName(final MultipartFile file) {
        String extension = getExtension(file);
        return UUID.randomUUID() + "." + extension;
    }

    private String getExtension(final MultipartFile file) {
        return file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf(".") + 1);
    }

    @SneakyThrows
    private void saveImage(final InputStream inputStream, final String fileName) {
        minioClient.putObject(PutObjectArgs.builder()
                .stream(inputStream, inputStream.available(), -1)
                .bucket(minioProperties.getBucket())
                .object(fileName)
                .build());
    }
}
